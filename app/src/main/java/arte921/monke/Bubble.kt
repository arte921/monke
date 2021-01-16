package arte921.monke

import java.lang.Math.random
import kotlin.math.pow

class Bubble (maxX: Float, maxY: Float) {
    val straal = 100.0F
    val x = straal + random().toFloat() * (maxX - 2 * straal)
    val y = straal + random().toFloat() * (maxY - 2 * straal)

    fun bevatCoordinaat (cx: Float, cy: Float): Boolean = (cx - x).pow(2) + (cy - y).pow(2) < straal.pow(2)
}