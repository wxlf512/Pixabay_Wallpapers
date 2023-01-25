package dev.wxlf.pixabaywallpapers.presentation.viewmodels.utils

import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity

sealed class ImagesViewState {
    data class CategoryLoaded(val images: List<ImageEntity>) : ImagesViewState()
}