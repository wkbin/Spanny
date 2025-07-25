package top.wkbin.spanny

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.ImageSpan
import androidx.core.content.ContextCompat

class DslSpannableStringBuildImpl(val builder: SpannableStringBuilder) : DslSpannableStringBuilder {

    // 记录上次添加文字后索引的值
    private var lastIndex = 0

    override fun addText(text: String, method: (DslSpanBuilder.() -> Unit)?) {
        val start = lastIndex
        builder.append(text)
        lastIndex += text.length
        val spanBuilder = DslSpanBuilderImpl()
        method?.let { spanBuilder.it() }
        spanBuilder.build().let { (spans, clickableSpan) ->
            spans.forEach {
                builder.setSpan(it, start, lastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
            clickableSpan?.let {
                builder.setSpan(it, start, lastIndex, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }
        }
    }

    @SuppressLint("InlinedApi")
    override fun addImage(context: Context, imgId: Int, width: Int, height: Int) {
        val start = builder.length
        builder.append("\uFFFC")

        val density = context.resources.displayMetrics.density
        val widthPx = (width * density).toInt()
        val heightPx = (height * density).toInt()

        val drawable = ContextCompat.getDrawable(context, imgId)?.mutate()?.apply {
            setBounds(0, 0, widthPx, heightPx)
        }

        drawable?.let {
            builder.setSpan(
                ImageSpan(it),
                start,
                start + 1,
                Spannable.SPAN_INCLUSIVE_EXCLUSIVE
            )
        }
        lastIndex = builder.length
    }

    override fun addImage(context: Context, bitmap: Bitmap) {
        val start = builder.length
        builder.append("\uFFFC")
        builder.setSpan(
            ImageSpan(context, bitmap),
            start,
            start + 1,
            Spannable.SPAN_INCLUSIVE_EXCLUSIVE
        )
        lastIndex = builder.length
    }

    fun build(): SpannableStringBuilder {
        return builder
    }
}