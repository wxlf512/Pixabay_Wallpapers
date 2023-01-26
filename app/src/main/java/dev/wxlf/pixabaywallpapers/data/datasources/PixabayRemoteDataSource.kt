package dev.wxlf.pixabaywallpapers.data.datasources

import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity

interface PixabayRemoteDataSource {
    suspend fun loadCategory(category: String, page: Int) : CategoryEntity
    suspend fun loadImage(id: Int) : CategoryEntity

    suspend fun loadCategoryImage(category: String) : CategoryEntity
}