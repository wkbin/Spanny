package top.wkbin.spanny.span

import android.graphics.Canvas
import android.graphics.LinearGradient
import android.graphics.Paint
import android.graphics.Shader
import android.text.TextPaint
import android.text.style.ReplacementSpan
import androidx.annotation.ColorInt

class GradientColorSpan(
    @ColorInt private val colors: IntArray,
    private val orientation: Orientation = Orientation.HORIZONTAL
) : ReplacementSpan() {
    enum class Orientation {
        HORIZONTAL, VERTICAL
    }

    private var gradientShader: Shader? = null

    override fun getSize(
        paint: Paint,
        text: CharSequence,
        start: Int,
        end: Int,
        fm: Paint.FontMetricsInt?
    ): Int {
        return paint.measureText(text, start, end).toInt()
    }

    override fun draw(
        canvas: Canvas,
        text: CharSequence,
        start: Int,
        end: Int,
        x: Float,
        top: Int,
        baseline: Int,
        bottom: Int,
        paint: Paint
    ) {
        val textPaint = paint as? TextPaint ?: TextPaint(paint)
        val textWidth = textPaint.measureText(text, start, end)
        val textHeight = bottom - top.toFloat()

        // 创建或更新渐变着色器
        if (gradientShader == null) {
            gradientShader = when (orientation) {
                Orientation.HORIZONTAL -> LinearGradient(
                    x, baseline.toFloat(),
                    x + textWidth, baseline.toFloat(),
                    colors,
                    null,
                    Shader.TileMode.CLAMP
                )
                Orientation.VERTICAL -> LinearGradient(
                    x, top.toFloat(),
                    x, top + textHeight,
                    colors,
                    null,
                    Shader.TileMode.CLAMP
                )
            }
        }

        textPaint.shader = gradientShader
        canvas.drawText(text, start, end, x, baseline.toFloat(), textPaint)

        // 重置着色器（避免影响其他文本）
        textPaint.shader = null
    }
}