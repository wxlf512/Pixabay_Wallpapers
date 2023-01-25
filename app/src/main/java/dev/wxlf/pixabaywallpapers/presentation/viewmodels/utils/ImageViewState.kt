package dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils

sealed class ImageViewState {
    data class ImageLoaded(val imageUrl: String) : ImageViewState()
}
