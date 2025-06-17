package top.wkbin.spanny

import android.text.SpannableStringBuilder
import android.text.method.LinkMovementMethod
import android.widget.TextView

fun TextView.buildDslSpannableString(init: DslSpannableStringBuilder.() -> Unit) {
    // 具体实现类
    val spanStringBuilderImpl = DslSpannableStringBuildImpl(SpannableStringBuilder())
    spanStringBuilderImpl.init()
    movementMethod = LinkMovementMethod.getInstance()
    // 通过实现类返回SpannableStringBuilder
    text =  spanStringBuilderImpl.build()
}