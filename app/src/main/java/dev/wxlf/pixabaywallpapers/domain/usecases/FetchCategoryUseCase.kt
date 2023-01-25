package dev.wxlf.pixabaywallpapers.domain.usecases

import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity
import dev.wxlf.pixabaywallpapers.domain.PixabayRepository

class FetchCategoryUseCase(private val repository: PixabayRepository) {

    suspend fun execute(category: String): ArrayList<ImageEntity> =
        repository.fetchCategory(category).hits
}