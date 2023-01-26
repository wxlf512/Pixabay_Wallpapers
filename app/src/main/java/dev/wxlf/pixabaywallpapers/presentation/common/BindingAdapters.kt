package dev.wxlf.pixabaywallpapers.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import dev.wxlf.pixabaywallpapers.R

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.NONE)
            .centerCrop()
            .error(R.drawable.no_image_icon)
            .into(view)
    }
}