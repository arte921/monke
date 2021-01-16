package arte921.monke

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.RadioButton
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    lateinit var spelView: BubbleView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        spelView = findViewById(R.id.bubbleView)
        val scoreView = findViewById<TextView>(R.id.scoreView)

        spelView.scoreVeranderdCallback = { score -> scoreView.text = score.toString() }
    }

    fun hoeveelheidVeranderd (view: View) {
        if (view is RadioButton) {
            val actief = view.isChecked

            when (view.getId()) {
                R.id.optie_2 -> if (actief) spelView.nieuwSpel(2)
                R.id.optie_3 -> if (actief) spelView.nieuwSpel(3)
                R.id.optie_4 -> if (actief) spelView.nieuwSpel(4)
            }
        }
    }
}