package ireny.escobar.mygithubapp.utils

import android.graphics.*
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation
import java.security.MessageDigest
import kotlin.math.min


class CircleTransform : BitmapTransformation() {

    override fun transform(
        pool: BitmapPool,
        toTransform: Bitmap,
        outWidth: Int,
        outHeight: Int
    ): Bitmap {
        return circleCrop(pool,toTransform)
    }

    private fun circleCrop(pool: BitmapPool, source: Bitmap): Bitmap {

        val size = min(source.width, source.height)
        val x = (source.width - size) / 2
        val y = (source.height - size) / 2

        val squared = Bitmap.createBitmap(source, x, y, size, size)

        var result: Bitmap? = pool.get(size, size, Bitmap.Config.ARGB_8888)
        if (result == null) {
            result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888)
        }

        result?.also {
            val canvas = Canvas(it)
            val paint = Paint()
            paint.shader = BitmapShader(
                squared,
                Shader.TileMode.CLAMP,
                Shader.TileMode.CLAMP
            )
            paint.isAntiAlias = true
            val r = size / 2f
            canvas.drawCircle(r, r, r, paint)

            return it
        }

        return source
    }

    override fun updateDiskCacheKey(messageDigest: MessageDigest) {
    }

}