package dev.wxlf.pixabaywallpapers.data

import dev.wxlf.pixabaywallpapers.BuildConfig
import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface PixabayAPI {

    @GET("./?key=${BuildConfig.PIXABAY_API_KEY}")
    suspend fun loadCategory(@Query("category") category: String) : CategoryEntity

    @GET("./?key=${BuildConfig.PIXABAY_API_KEY}")
    suspend fun loadImage(@Query("id") id: Int) : CategoryEntity
}
