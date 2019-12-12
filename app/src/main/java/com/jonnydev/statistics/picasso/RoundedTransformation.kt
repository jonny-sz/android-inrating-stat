package com.jonnydev.statistics.picasso

import android.graphics.*
import com.squareup.picasso.Transformation

class RoundedTransformation(
    private val mRadius: Int,
    private val mMargin: Int
) : Transformation {
    override fun transform(source: Bitmap): Bitmap {
        val paint = Paint().apply {
            isAntiAlias = true
            shader = BitmapShader(
                source,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
        }
        val output = Bitmap.createBitmap(
            source.width,
            source.height,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(output)
        val rectF = RectF(
            mMargin.toFloat(),
            mMargin.toFloat(),
            (source.width - mMargin).toFloat(),
            (source.height - mMargin).toFloat()
        )

        canvas.drawRoundRect(rectF, mRadius.toFloat(), mRadius.toFloat(), paint)

        if (source != output) {
            source.recycle()
        }

        return output
    }

    override fun key(): String {
        return "rounded(r=$mRadius, m=$mMargin)"
    }
}
