package dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils

sealed class ImageViewState {
    object LoadingImage : ImageViewState()
    data class ImageLoaded(val imageUrl: String) : ImageViewState()
    data class ErrorState(val message: String) : ImageViewState()
}
