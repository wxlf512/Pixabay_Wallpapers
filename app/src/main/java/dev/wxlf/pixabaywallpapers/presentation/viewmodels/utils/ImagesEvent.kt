package dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils

sealed class ImagesEvent {
    data class LoadCategory(val category: String) : ImagesEvent()
    object LoadMoreImages : ImagesEvent()
}
