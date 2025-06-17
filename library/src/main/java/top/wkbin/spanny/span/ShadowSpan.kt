package top.wkbin.spanny.span

import android.text.TextPaint
import android.text.style.CharacterStyle
import androidx.annotation.ColorInt

class ShadowSpan(
    private val radius: Float,
    private val dx: Float,
    private val dy: Float,
    @ColorInt private val shadowColor: Int
) : CharacterStyle() {

    override fun updateDrawState(tp: TextPaint) {
        tp.setShadowLayer(radius, dx, dy, shadowColor)
    }
}