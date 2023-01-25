package dev.wxlf.pixabaywallpapers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.pixabaywallpapers.data.PixabayAPI
import dev.wxlf.pixabaywallpapers.data.PixabayRepositoryImpl
import dev.wxlf.pixabaywallpapers.data.datasources.PixabayRemoteDataSource
import dev.wxlf.pixabaywallpapers.data.datasources.RetrofitPixabayDataSource
import dev.wxlf.pixabaywallpapers.domain.PixabayRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {

    @Provides
    @Singleton
    fun provideRetrofitPixabayDataSource(pixabayAPI: PixabayAPI): PixabayRemoteDataSource =
        RetrofitPixabayDataSource(pixabayAPI)

    @Provides
    @Singleton
    fun providePixabayRepository(remoteDataSource: PixabayRemoteDataSource): PixabayRepository =
        PixabayRepositoryImpl(remoteDataSource)
}