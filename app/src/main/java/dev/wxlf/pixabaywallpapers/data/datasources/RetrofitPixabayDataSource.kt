package dev.wxlf.pixabaywallpapers.data.datasources

import dev.wxlf.pixabaywallpapers.data.PixabayAPI
import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity

class RetrofitPixabayDataSource(private val pixabayAPI: PixabayAPI) :
    PixabayRemoteDataSource {
    override suspend fun loadCategory(category: String, page: Int): CategoryEntity =
        pixabayAPI.loadCategory(category, page)

    override suspend fun loadImage(id: Int): CategoryEntity =
        pixabayAPI.loadImage(id)

    override suspend fun loadCategoryImage(category: String): CategoryEntity =
        pixabayAPI.loadCategoryImage(category)
}