package dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils

sealed class ImageEvent {
    data class LoadImage(val id: Int) : ImageEvent()
}