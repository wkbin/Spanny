package top.wkbin.spanny

import android.graphics.Typeface
import android.text.style.*
import android.view.View

class DslSpanBuilderImpl : DslSpanBuilder {
    // 使用集合存储所有生成的 Span 对象
    private val spans = mutableListOf<Any>()
    private var currentStyle = Typeface.NORMAL

    // 特殊处理点击事件，因为需要回调
    private var onClickAction: ((View) -> Unit)? = null

    override fun textColor(color: Int) = apply {
        spans.add(ForegroundColorSpan(color))
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

    override fun fontFamily(typeface: Typeface) = apply {
        spans.add(TypefaceSpan(typeface))
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
            }
        }
        return spans to clickableSpan
    }
}
