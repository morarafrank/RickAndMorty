package com.morarafrank.rickandmorty.di

import com.morarafrank.rickandmorty.data.remote.RickAndMortyService
import com.morarafrank.rickandmorty.utils.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideConverterFactory(): Converter.Factory {
        return GsonConverterFactory.create()
    }

    //Repos

    // Viewmodel

    // Base url
    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = BASE_URL


    // HttpClient
    @Singleton
    @Provides
    fun provideHttpClient(interceptors: Set<@JvmSuppressWildcards Interceptor>): OkHttpClient {
        return OkHttpClient.Builder()
            .readTimeout(180, TimeUnit.SECONDS)
            .connectTimeout(180, TimeUnit.SECONDS)
            .apply {
                interceptors().addAll(interceptors)
            }
            .build()
    }

    // Interceptors
    @Provides
    fun provideInterceptors(): Set<Interceptor> {
        return setOf(
            Interceptor { chain ->
                val request = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .build()
                chain.proceed(request)
            }
        )
    }

    @Provides
    @Singleton
    fun provideJson(): Json = Json {
        ignoreUnknownKeys = true
        isLenient = true
    }


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        converterFactory: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(converterFactory)
            .client(okHttpClient)
            .build()
    }

    @Provides
    @Singleton
    fun RickAndMortyService(retrofit: Retrofit): RickAndMortyService {
        return retrofit.create(RickAndMortyService::class.java)
    }


}