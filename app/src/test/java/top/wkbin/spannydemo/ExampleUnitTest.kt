package top.wkbin.spannydemo

import DslSpanBuilderImpl
import android.graphics.Typeface
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testBoldSpanApplied() {
        val spanBuilder = DslSpanBuilderImpl()
        spanBuilder.bold()
        val (spans, _) = spanBuilder.build()
        assertTrue("Bold span should be applied", spans.any { it is StyleSpan && it.style == Typeface.BOLD })
    }

    @Test
    fun testTextColorSpanApplied() {
        val testColor = 0xFF0000 // 红色
        val spanBuilder = DslSpanBuilderImpl()
        spanBuilder.textColor(testColor)
        val (spans, _) = spanBuilder.build()
        assertTrue("Text color span should be applied", spans.any { it is ForegroundColorSpan && it.foregroundColor == testColor })
    }
}