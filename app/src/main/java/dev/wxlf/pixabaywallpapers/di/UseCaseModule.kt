package dev.wxlf.pixabaywallpapers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.pixabaywallpapers.domain.PixabayRepository
import dev.wxlf.pixabaywallpapers.domain.usecases.FetchCategoryUseCase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    @Singleton
    fun provideFetchCategoryUseCase(repository: PixabayRepository): FetchCategoryUseCase =
        FetchCategoryUseCase(repository)
}