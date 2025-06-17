package top.wkbin.spannydemo

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("top.wkbin.spanny", appContext.packageName)
    }

    @Test
    fun testClickSpanFunctionality() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        val textView = TextView(appContext)
        val spannable = DslSpannableStringBuildImpl().apply {
            addText("Click me") { click { assertTrue("Click event triggered", true) } }
        }.build()
        textView.text = spannable
        // 模拟点击操作
        textView.performClick()
    }
}