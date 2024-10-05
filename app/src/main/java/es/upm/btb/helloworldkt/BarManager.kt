package es.upm.btb.helloworldkt

import android.content.Context
import org.osmdroid.util.GeoPoint
import java.io.BufferedReader
import java.io.InputStreamReader

object BarManager {
    var barList: MutableList<Bar> = mutableListOf()
    // You can add functions here to add, remove, or update the bars list


    fun loadBarsFromAssets(context: Context) {
        val input = InputStreamReader(context.assets.open("bars.csv"));
        val reader = BufferedReader(input)

        reader.forEachLine { line ->
            val c = line.split(",")
            if (c.size >= 2) {
                val id: Int = c[0].toInt()
                val location: GeoPoint = GeoPoint(c[2].toDouble(), c[3].toDouble())
                val rating: Float = c[6].toFloat()
                barList.add(
                    Bar(id, c[1], location, c[4], c[5], rating, c[7], false)
                )
//                Log.i(TAG, barList.toString())
            } else {
                // Handle incomplete or malformed lines
                println("Skipping malformed line: $line")
            }
        }
    }


}

data class Bar(
    val id: Int,
    val name: String,
    val location: GeoPoint,
    val description: String,
    val price: String,
    val rating: Float,
    val imageUrl: String,
    var isChecked: Boolean
)