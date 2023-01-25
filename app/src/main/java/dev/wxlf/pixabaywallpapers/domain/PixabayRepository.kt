package dev.wxlf.pixabaywallpapers.domain

import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity
import retrofit2.http.Query

interface PixabayRepository {
    suspend fun fetchCategory(@Query("category") category: String) : CategoryEntity
}