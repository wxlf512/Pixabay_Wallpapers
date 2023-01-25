package dev.wxlf.pixabaywallpapers.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.wxlf.pixabaywallpapers.data.PixabayAPI
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(httpLoggingInterceptor: HttpLoggingInterceptor): OkHttpClient =
        OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build()

    @Provides
    @Singleton
    fun provideGson() : GsonConverterFactory = GsonConverterFactory.create()

    @Provides
    @Singleton
    fun providePixabayAPI(okHttpClient: OkHttpClient, gson: GsonConverterFactory): PixabayAPI {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://pixabay.com/api/")
            .client(okHttpClient)
            .addConverterFactory(gson)
            .build()
        return retrofit.create(PixabayAPI::class.java)
    }
}