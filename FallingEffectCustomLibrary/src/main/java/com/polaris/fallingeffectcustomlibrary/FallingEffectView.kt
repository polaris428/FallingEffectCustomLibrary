package com.polaris.fallingeffectcustomlibrary

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.os.Handler
import android.os.HandlerThread
import android.util.AttributeSet
import android.view.View
import androidx.core.graphics.drawable.toBitmap
import com.example.fallingeffectview.Randomizer
import com.example.fallingeffectview.Snowflake

class FallingEffectView (context: Context, attrs: AttributeSet) : View(context, attrs) {


     var creationResourcesNum : Int
     var fallingImage: Bitmap?
     var fallingImageAlphaMin: Int
     var fallingImageAlphaMax: Int
     var fallingImageAngleMax: Int
     var fallingImageSizeMin: Int
     var fallingImageSizeMax: Int
     var fallingImageSpeedMin: Int
     var fallingImageSpeedMax: Int
     var fallingImageFadingEnabled: Boolean
     var alreadyFallingImage: Boolean

    private lateinit var updateSnowflakesThread: UpdateSnowflakesThread
    private var snowflakes: Array<Snowflake>? = null

    init {
        val a = context.obtainStyledAttributes(attrs, R.styleable.FlowingEffectView)
        try {
            creationResourcesNum = a.getInt(R.styleable.FlowingEffectView_creationResourcesNum, DEFAULT_SNOWFLAKES_NUM)
            fallingImage = a.getDrawable(R.styleable.FlowingEffectView_fallingImage)?.toBitmap()
            fallingImageAlphaMin = a.getInt(R.styleable.FlowingEffectView_fallingImageAlphaMin, DEFAULT_SNOWFLAKE_ALPHA_MIN)
            fallingImageAlphaMax = a.getInt(R.styleable.FlowingEffectView_fallingImageAlphaMax, DEFAULT_SNOWFLAKE_ALPHA_MAX)
            fallingImageAngleMax = a.getInt(R.styleable.FlowingEffectView_fallingImageAngleMax, DEFAULT_SNOWFLAKE_ANGLE_MAX)
            fallingImageSizeMin = a.getDimensionPixelSize(R.styleable.FlowingEffectView_fallingImageSizeMin, dpToPx(
                DEFAULT_SNOWFLAKE_SIZE_MIN_IN_DP
            ))
            fallingImageSizeMax = a.getDimensionPixelSize(R.styleable.FlowingEffectView_fallingImageSizeMax, dpToPx(
                DEFAULT_SNOWFLAKE_SIZE_MAX_IN_DP
            ))
            fallingImageSpeedMin = a.getInt(R.styleable.FlowingEffectView_fallingImageSpeedMin, DEFAULT_SNOWFLAKE_SPEED_MIN)
            fallingImageSpeedMax = a.getInt(R.styleable.FlowingEffectView_fallingImageSpeedMax, DEFAULT_SNOWFLAKE_SPEED_MAX)
            fallingImageFadingEnabled = a.getBoolean(R.styleable.FlowingEffectView_fallingImageFadingEnabled, DEFAULT_SNOWFLAKES_FADING_ENABLED)
            alreadyFallingImage = a.getBoolean(R.styleable.FlowingEffectView_alreadyFallingImage, DEFAULT_SNOWFLAKES_ALREADY_FALLING)

            setLayerType(LAYER_TYPE_HARDWARE, null)

        } finally {
            a.recycle()
        }
    }

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        updateSnowflakesThread = UpdateSnowflakesThread()
    }

    override fun onDetachedFromWindow() {
        updateSnowflakesThread.quit()
        super.onDetachedFromWindow()
    }

    private fun dpToPx(dp: Int): Int {
        return (dp * resources.displayMetrics.density).toInt()
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        snowflakes = createSnowflakes()
    }

    override fun onVisibilityChanged(changedView: View, visibility: Int) {
        super.onVisibilityChanged(changedView, visibility)
        if (changedView === this && visibility == GONE) {
            snowflakes?.forEach { it.reset() }
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isInEditMode) {
            return
        }

        var haveAtLeastOneVisibleSnowflake = false

        val localSnowflakes = snowflakes
        if (localSnowflakes != null) {
            for (snowflake in localSnowflakes) {
                if (snowflake.isStillFalling()) {
                    haveAtLeastOneVisibleSnowflake = true
                    snowflake.draw(canvas)
                }
            }
        }

        if (haveAtLeastOneVisibleSnowflake) {
            updateSnowflakes()
        } else {
            visibility = GONE
        }

        val fallingSnowflakes = snowflakes?.filter { it.isStillFalling() }
        if (fallingSnowflakes?.isNotEmpty() == true) {
            fallingSnowflakes.forEach { it.draw(canvas) }
            updateSnowflakes()
        }
        else {
            visibility = GONE
        }
    }

    fun stopFalling() {
        snowflakes?.forEach { it.shouldRecycleFalling = false }
    }

    fun restartFalling() {
        snowflakes?.forEach { it.shouldRecycleFalling = true }
    }

    private fun createSnowflakes(): Array<Snowflake> {
        val randomizer = Randomizer()

        val snowflakeParams = Snowflake.Params(
            parentWidth = width,
            parentHeight = height,
            image = fallingImage,
            alphaMin = fallingImageAlphaMin,
            alphaMax = fallingImageAlphaMax,
            angleMax = fallingImageAngleMax,
            sizeMinInPx = fallingImageSizeMin,
            sizeMaxInPx = fallingImageSizeMax,
            speedMin = fallingImageSpeedMin,
            speedMax = fallingImageSpeedMax,
            fadingEnabled = fallingImageFadingEnabled,
            alreadyFalling = alreadyFallingImage
        )

        return Array(creationResourcesNum) { Snowflake(randomizer, snowflakeParams) }
    }

    private fun updateSnowflakes() {
        updateSnowflakesThread.handler.post {
            var haveAtLeastOneVisibleSnowflake = false

            val localSnowflakes = snowflakes ?: return@post

            for (snowflake in localSnowflakes) {
                if (snowflake.isStillFalling()) {
                    haveAtLeastOneVisibleSnowflake = true
                    snowflake.update()
                }
            }

            if (haveAtLeastOneVisibleSnowflake) {
                postInvalidateOnAnimation()
            }
        }
    }

    private class UpdateSnowflakesThread : HandlerThread("SnowflakesComputations") {
        val handler: Handler

        init {
            start()
            handler = Handler(looper)
        }
    }

    companion object {
        private const val DEFAULT_SNOWFLAKES_NUM = 200
        private const val DEFAULT_SNOWFLAKE_ALPHA_MIN = 150
        private const val DEFAULT_SNOWFLAKE_ALPHA_MAX = 250
        private const val DEFAULT_SNOWFLAKE_ANGLE_MAX = 10
        private const val DEFAULT_SNOWFLAKE_SIZE_MIN_IN_DP = 2
        private const val DEFAULT_SNOWFLAKE_SIZE_MAX_IN_DP = 8
        private const val DEFAULT_SNOWFLAKE_SPEED_MIN = 2
        private const val DEFAULT_SNOWFLAKE_SPEED_MAX = 8
        private const val DEFAULT_SNOWFLAKES_FADING_ENABLED = false
        private const val DEFAULT_SNOWFLAKES_ALREADY_FALLING = false
    }
}