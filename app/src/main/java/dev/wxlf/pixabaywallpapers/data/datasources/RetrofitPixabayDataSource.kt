package dev.wxlf.pixabaywallpapers.data.datasources

import dev.wxlf.pixabaywallpapers.data.PixabayAPI
import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity

class RetrofitPixabayDataSource(private val pixabayAPI: PixabayAPI) :
    PixabayRemoteDataSource {
    override suspend fun loadCategory(category: String): CategoryEntity =
        pixabayAPI.loadCategory(category)

    override suspend fun loadImage(id: Int): CategoryEntity =
        pixabayAPI.loadImage(id)
}