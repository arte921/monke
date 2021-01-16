package arte921.monke

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.core.content.res.ResourcesCompat

class BubbleView (context: Context, attrs: AttributeSet): View(context, attrs) {

    private lateinit var extraCanvas: Canvas
    private lateinit var extraBitmap: Bitmap

    private val achtergrondKleur = Color.parseColor("#000000");
    private val belKleur = Color.parseColor("#FFFFFF");

    private var bellen = mutableListOf<Bubble>()

    private var hoogte = 0
    private var breedte = 0

    private val belPaint = Paint().apply {
        color = belKleur
        isAntiAlias = true
        isDither = true
        style = Paint.Style.FILL
        strokeJoin = Paint.Join.ROUND
        strokeCap = Paint.Cap.ROUND
        strokeWidth = 1f
    }

    private fun voegBelToe () {
        bellen.add(Bubble(breedte.toFloat(), hoogte.toFloat()))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        if(::extraBitmap.isInitialized) extraBitmap.recycle()
        extraBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888)
        extraCanvas = Canvas(extraBitmap)
        extraCanvas.drawColor(achtergrondKleur)

        hoogte = h
        breedte = w

        for (i in 1..2) {
            voegBelToe()
        }

        invalidate()
    }

    override fun onDraw(canvas: Canvas){
        super.onDraw(canvas)
        canvas.drawBitmap(extraBitmap,0F,0F,null)

        for (bel in bellen) {
            canvas.drawCircle(bel.x, bel.y, bel.straal, belPaint)
        }
    }

    override fun onTouchEvent(e: MotionEvent): Boolean {
        if (bellen[0].bevatCoordinaat(e.x, e.y)) {
            bellen.removeAt(0)
            voegBelToe()
        }

        invalidate()
        return true
    }
}