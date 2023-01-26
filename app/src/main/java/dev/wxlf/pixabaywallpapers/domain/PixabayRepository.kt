package dev.wxlf.pixabaywallpapers.domain

import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity

interface PixabayRepository {
    suspend fun fetchCategory(category: String, page: Int) : CategoryEntity
    suspend fun fetchImage(id: Int) : CategoryEntity

    suspend fun fetchCategoryImage(category: String) : CategoryEntity
}