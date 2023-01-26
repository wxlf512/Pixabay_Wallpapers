package dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils

import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity

sealed class ImagesViewState {
    object LoadingCategory : ImagesViewState()
    data class CategoryLoaded(val images: List<ImageEntity>) : ImagesViewState()
    data class ErrorState(val message: String) : ImagesViewState()
}