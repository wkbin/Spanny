package top.wkbin.spanny

import android.content.Context
import android.graphics.Bitmap

interface DslSpannableStringBuilder {

    /**
     * 增加一段文字
     */
    fun addText(text: String, method: (DslSpanBuilder.() -> Unit)? = null)

    /**
     * 增加一张图片
     */
    fun addImage(context: Context, imgId: Int)
    fun addImage(context: Context, bitmap: Bitmap)
}