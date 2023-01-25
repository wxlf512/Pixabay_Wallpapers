package dev.wxlf.pixabaywallpapers.presentation.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

object BindingAdapters {

    @JvmStatic
    @BindingAdapter("app:imageUrl")
    fun loadImage(view: ImageView, url: String?) {
        Glide.with(view).load(url).centerCrop().into(view)
    }
}