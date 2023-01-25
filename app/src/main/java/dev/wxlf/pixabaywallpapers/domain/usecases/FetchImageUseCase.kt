package dev.wxlf.pixabaywallpapers.domain.usecases

import dev.wxlf.pixabaywallpapers.data.entities.ImageEntity
import dev.wxlf.pixabaywallpapers.domain.PixabayRepository

class FetchImageUseCase(private val repository: PixabayRepository) {

    suspend fun execute(id: Int): ImageEntity =
        repository.fetchImage(id).hits[0]
}