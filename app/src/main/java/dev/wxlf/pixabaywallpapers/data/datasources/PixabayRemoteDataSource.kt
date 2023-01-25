package dev.wxlf.pixabaywallpapers.data.datasources

import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity
import retrofit2.http.Query

interface PixabayRemoteDataSource {
    suspend fun loadCategory(@Query("category") category: String) : CategoryEntity
}