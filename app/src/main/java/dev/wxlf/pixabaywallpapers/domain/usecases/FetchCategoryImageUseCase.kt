package dev.wxlf.pixabaywallpapers.domain.usecases

import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity
import dev.wxlf.pixabaywallpapers.domain.PixabayRepository

class FetchCategoryImageUseCase(private val repository: PixabayRepository) {

    suspend fun execute(category: String): ImageEntity =
        repository.fetchCategoryImage(category).hits[0]
}