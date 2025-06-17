package top.wkbin.spanny

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.text.TextPaint
import android.text.style.*
import android.view.View
import top.wkbin.spanny.DslSpanBuilder.VerticalAlignment
import top.wkbin.spanny.span.GradientColorSpan
import top.wkbin.spanny.span.ShadowSpan

class DslSpanBuilderImpl : DslSpanBuilder {
    // 使用集合存储所有生成的 Span 对象
    private val spans = mutableListOf<Any>()
    private var currentStyle = Typeface.NORMAL

    // 特殊处理点击事件，因为需要回调
    private var onClickAction: ((View) -> Unit)? = null
    private var clickHighlightColor: Int? = null

    override fun textColor(color: Int) = apply {
        spans.add(ForegroundColorSpan(color))
    }

    override fun colorGradient(
        vararg colors: Int,
        orientation: GradientColorSpan.Orientation
    ): DslSpanBuilder = apply {
        if (colors.size < 2) {
            // 至少需要2个颜色
            spans.add(ForegroundColorSpan(colors.firstOrNull() ?: Color.BLACK))
        } else {
            spans.add(GradientColorSpan(colors, orientation))
        }
    }

    override fun backgroundColor(color: Int) = apply {
        spans.add(BackgroundColorSpan(color))
    }

    override fun textSize(size: Int) = apply {
        spans.add(AbsoluteSizeSpan(size, true))
    }

    override fun click(block: (View) -> Unit) = apply {
        onClickAction = block
    }

    override fun bold() = apply {
        currentStyle = currentStyle or Typeface.BOLD
        updateStyleSpan()
    }

    override fun italic() = apply {
        currentStyle = currentStyle or Typeface.ITALIC
        updateStyleSpan()
    }

    override fun underline() = apply {
        spans.add(UnderlineSpan())
    }

    override fun strikethrough() = apply {
        spans.add(StrikethroughSpan())
    }

    @SuppressLint("NewApi")
    override fun fontFamily(typeface: Typeface) = apply {
        spans.add(TypefaceSpan(typeface))
    }

    override fun relativeSize(ratio: Float) = apply {
        spans.add(RelativeSizeSpan(ratio))
    }

    override fun shadow(radius: Float, dx: Float, dy: Float, color: Int) = apply {
        spans.add(ShadowSpan(radius, dx, dy, color))
    }

    override fun clickHighlightColor(color: Int) = apply {
        clickHighlightColor = color
    }

    override fun verticalAlignment(alignment: VerticalAlignment) = apply {
        spans.add(when (alignment) {
            is VerticalAlignment.Superscript -> SuperscriptSpan()
            is VerticalAlignment.Subscript -> SubscriptSpan()
            else -> object : MetricAffectingSpan() {
                override fun updateDrawState(tp: TextPaint) {}
                override fun updateMeasureState(tp: TextPaint) {}
            }
        })
    }

    override fun customSpan(span: Any) = apply {
        spans.add(span)
    }

    private fun updateStyleSpan() {
        // 移除旧的 StyleSpan（如果存在）
        spans.removeAll { it is StyleSpan }
        spans.add(StyleSpan(currentStyle))
    }

    // 获取所有 Span 和点击事件
    fun build(): Pair<List<Any>, ClickableSpan?> {
        val clickableSpan = onClickAction?.let { action ->
            object : ClickableSpan() {
                override fun onClick(widget: View) = action(widget)

                override fun updateDrawState(ds: TextPaint) {
                    clickHighlightColor?.let { color ->
                        ds.bgColor = color
                    }
                }
            }
        }
        return spans to clickableSpan
    }
}
