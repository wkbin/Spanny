package top.wkbin.spannydemo

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import top.wkbin.spanny.buildDslSpannableString
import androidx.core.graphics.toColorInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }


        findViewById<TextView>(R.id.tv_demo).buildDslSpannableString {
            addText("16sp大小") { textSize(16) }
            addText("\n")
            addText("24sp加粗") { textSize(24).bold() }
            addText("\n")
            addText("绿色斜体") { textColor("#00FF00".toColorInt()).italic() }
            addText("\n")
            addText("红色带下划线") { textColor(0xFFE91E63.toInt()).underline() }
            addText("\n")
            addText("删除线") { strikethrough() }
            addText("\n")
            val customTypeface =
                ResourcesCompat.getFont(this@MainActivity, R.font.sansita_extra_bold_italic)
            addText("20sp红色背景自定义字体") {
                fontFamily(customTypeface!!).textSize(20).backgroundColor(0xFFE91E63.toInt())
            }
            addText("\n")
            addText("可点击1") {
                click { showToast("点击了可点击文本1") }
            }
            addImage(this@MainActivity, R.mipmap.ic_launcher)
            addText("可点击2") {
                click { showToast("点击了可点击文本1") }
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}