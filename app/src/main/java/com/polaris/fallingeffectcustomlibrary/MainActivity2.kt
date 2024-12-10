package com.polaris.fallingeffectcustomlibrary

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.util.TypedValue
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.polaris.fallingeffectcustomlibrary.R
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fallingEffectView = findViewById<FallingEffectView>(R.id.fallingEffectView)

// 설정된 속성을 코드로 변환
        fallingEffectView.apply {
            creationResourcesNum = 50 // app:creationResourcesNum="50"
            val drawable = ContextCompat.getDrawable(context, R.drawable.ic_launcher_background)
            fallingImage = drawable?.let { drawableToBitmap(it) }
            fallingImageAlphaMin = 80 // app:fallingImageAlphaMin="80"
            fallingImageAlphaMax = 90 // app:fallingImageAlphaMax="90"
            fallingImageSizeMin = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                15f,
                resources.displayMetrics
            ).toInt() // app:fallingImageSizeMin="15dp"
            fallingImageSizeMax = TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP,
                20f,
                resources.displayMetrics
            ).toInt() // app:fallingImageSizeMax="20dp"
            fallingImageSpeedMin = 1 // app:fallingImageSpeedMin="1"
            fallingImageSpeedMax = 2 // app:fallingImageSpeedMax="2"
            alreadyFallingImage = true // app:alreadyFallingImage="true"
        }

    }

    fun drawableToBitmap(drawable: Drawable): Bitmap {
        if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }

        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}