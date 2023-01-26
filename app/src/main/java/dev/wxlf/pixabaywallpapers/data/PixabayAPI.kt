package dev.wxlf.pixabaywallpapers.data

import dev.wxlf.pixabaywallpapers.BuildConfig
import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("./?key=${BuildConfig.PIXABAY_API_KEY}")
    suspend fun loadCategory(@Query("category") category: String, @Query("page") page: Int) : CategoryEntity

    @GET("./?key=${BuildConfig.PIXABAY_API_KEY}")
    suspend fun loadImage(@Query("id") id: Int) : CategoryEntity

    @GET("./?key=${BuildConfig.PIXABAY_API_KEY}&per_page=3")
    suspend fun loadCategoryImage(@Query("category") category: String) : CategoryEntity
}
