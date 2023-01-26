package dev.wxlf.pixabaywallpapers.data

import dev.wxlf.pixabaywallpapers.data.datasources.PixabayRemoteDataSource
import dev.wxlf.pixabaywallpapers.data.entities.CategoryEntity
import dev.wxlf.pixabaywallpapers.domain.PixabayRepository

class PixabayRepositoryImpl(private val remoteDataSource: PixabayRemoteDataSource) : PixabayRepository {
    override suspend fun fetchCategory(category: String, page: Int): CategoryEntity =
        remoteDataSource.loadCategory(category, page)

    override suspend fun fetchImage(id: Int): CategoryEntity =
        remoteDataSource.loadImage(id)

    override suspend fun fetchCategoryImage(category: String): CategoryEntity =
        remoteDataSource.loadCategoryImage(category)
}