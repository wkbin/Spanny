package top.wkbin.spannydemo

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
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
            // 1. 主标题（渐变+阴影）
            addText("夏日清凉特惠季\n") {
                colorGradient(
                    "#FF6B6B".toColorInt(),
                    "#4ECDC4".toColorInt(),
                    "#556270".toColorInt()
                ).bold().relativeSize(1.8f)
                    .shadow(radius = 4f, dx = 2f, dy = 2f, color = "#30000000".toColorInt())
            }

            // 2. 副标题（斜体+下划线）
            addText("全场冰品限时优惠\n\n") {
                textColor("#556270".toColorInt()).italic().underline()
            }

            // 4. 促销信息（高亮背景+点击事件）
            addText("• 即日起至8月31日，")
            addText("第二杯半价！") {
                backgroundColor("#FFF9C4".toColorInt()).textColor("#E91E63".toColorInt()).bold()
                    .click {
                        showToast("查看活动详情")
                    }.clickHighlightColor("#40FF9800".toColorInt())
            }
            addText("\n")

            // 5. 产品列表（带自定义项目符号）
            addText(" 新鲜水果冰沙系列")
            addImage(context = this@MainActivity, imgId = R.drawable.ic_fruit)
            addText(" 限定抹茶特调")
            addImage(context = this@MainActivity, imgId = R.drawable.ic_matcha)
            addText("\n")
            addText(" 椰香芒果西米露")
            addImage(context = this@MainActivity, imgId = R.drawable.ic_mango)
            addText(" 巧克力脆脆冰")
            addImage(context = this@MainActivity, imgId = R.drawable.ic_chocolate)
            addText("\n")
            // 7. 价格信息（删除线+颜色变化）
            addText("原价: ")
            addText("¥35") { strikethrough().textColor(Color.GRAY) }
            addText("  特惠价: ")
            addText("¥25") { textColor("#FF5252".toColorInt()).relativeSize(1.3f).bold() }
            // 8. 倒计时（动态效果）
            addText("\n\n")
            addText("限时优惠剩余: ") { textColor("#556270".toColorInt()) }
            addText("02:15:36") {
                backgroundColor("#FFF9C4".toColorInt()).textColor("#E91E63".toColorInt()).bold()
                    .relativeSize(1.2f)
            }

            // 9. 行动按钮（圆角背景+点击事件）
            addText("\n\n")
            addText(" 立即抢购 → ") {
                backgroundColor("#FF6B6B".toColorInt()).textColor(Color.WHITE).bold()
                    .relativeSize(1.2f).click { showToast("开始下单") }
                    .clickHighlightColor("#40FF5722".toColorInt())
            }

            // 10. 底部信息（小字+图标）
            addText("\n\n")
            addImage(this@MainActivity, R.drawable.ic_info)
            addText(" 本活动最终解释权归商家所有") {
                textColor("#9E9E9E".toColorInt()).relativeSize(
                    0.9f
                )
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}