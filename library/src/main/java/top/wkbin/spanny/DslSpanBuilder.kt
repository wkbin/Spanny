package top.wkbin.spanny

import android.graphics.Typeface
import android.view.View
import androidx.annotation.ColorInt

interface DslSpanBuilder {
    /**
     * 设置字体颜色
     */
    fun textColor(@ColorInt color: Int): DslSpanBuilder

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
}