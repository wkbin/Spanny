package top.wkbin.spanny

import android.graphics.Typeface
import android.view.View
import androidx.annotation.ColorInt
import top.wkbin.spanny.span.GradientColorSpan

interface DslSpanBuilder {
    /**
     * 设置字体颜色
     */
    fun textColor(@ColorInt color: Int): DslSpanBuilder

    /**
     * 设置文字渐变颜色
     * @param colors 渐变颜色数组（至少需要2个颜色）
     * @param orientation 渐变方向（水平/垂直）
     */
    fun colorGradient(
        @ColorInt vararg colors: Int,
        orientation: GradientColorSpan.Orientation = GradientColorSpan.Orientation.HORIZONTAL
    ): DslSpanBuilder

    /**
     * 设置字体背景颜色
     */
    fun backgroundColor(@ColorInt color: Int): DslSpanBuilder

    /**
     * 设置字体大小
     */
    fun textSize(size: Int): DslSpanBuilder

    /**
     * 设置点击事件
     */
    fun click(block: (View) -> Unit): DslSpanBuilder

    /**
     * 设置粗体
     */
    fun bold(): DslSpanBuilder

    /**
     * 设置斜体
     */
    fun italic(): DslSpanBuilder

    /**
     * 设置下划线
     */
    fun underline(): DslSpanBuilder

    /**
     * 设置删除线
     */
    fun strikethrough(): DslSpanBuilder

    /**
     * 设置自定义字体
     */
    fun fontFamily(typeface: Typeface): DslSpanBuilder

    /**
     * 设置相对字体大小（基于默认大小的比例）
     */
    fun relativeSize(ratio: Float): DslSpanBuilder

    /**
     * 设置文字阴影效果
     */
    fun shadow(radius: Float, dx: Float, dy: Float, @ColorInt color: Int): DslSpanBuilder

    /**
     * 设置点击事件高亮颜色
     */
    fun clickHighlightColor(@ColorInt color: Int): DslSpanBuilder

    /**
     * 设置文字上标/下标
     */
    fun verticalAlignment(alignment: VerticalAlignment): DslSpanBuilder

    /**
     * 设置自定义替换式Span
     */
    fun customSpan(span: Any): DslSpanBuilder


    sealed class VerticalAlignment {
        object Normal : VerticalAlignment()
        object Superscript : VerticalAlignment()
        object Subscript : VerticalAlignment()
    }
}