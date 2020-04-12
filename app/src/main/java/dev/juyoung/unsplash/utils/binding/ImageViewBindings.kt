package dev.juyoung.unsplash.utils.binding

import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.juyoung.unsplash.R
import dev.juyoung.unsplash.extensions.deviceWidth

@BindingAdapter("app:load", "app:width", "app:height")
fun loadImage(view: ImageView, url: String?, width: Int?, height: Int?) {
    if (url == null || width == null || height == null) return

    url.let {
        val viewWidth = view.context.deviceWidth
        val viewHeight = height * (viewWidth / (width / 100)) / 100

        Glide.with(view)
            .load(it)
            .thumbnail(0.5f)
            .override(viewWidth, viewHeight)
            .format(DecodeFormat.PREFER_RGB_565)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(view)

        view.colorFilter = PorterDuffColorFilter(
            view.context.getColor(R.color.colorBlackOpaque20),
            PorterDuff.Mode.SRC_OVER
        )
    }
}
