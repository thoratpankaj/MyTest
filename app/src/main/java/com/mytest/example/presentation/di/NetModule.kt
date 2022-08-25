package com.mytest.example.presentation.di

import com.mytest.example.BuildConfig
import com.mytest.example.data.api.APIService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetModule {
    @Singleton
    @Provides
    fun provideRetroFit():Retrofit{

        val httpClient = OkHttpClient.Builder()

        httpClient.addInterceptor(Interceptor { chain ->
            val request: Request =
                chain.request().newBuilder()
                    .header("X-RapidAPI-Key", BuildConfig.API_KEY)
                    .header("X-RapidAPI-Host", BuildConfig.API_HOST)
                    .build()
            chain.proceed(request)
        })


        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BuildConfig.BASE_URL)
            .client(httpClient.build())
            .build()
    }

    @Singleton
    @Provides
    fun provideAPIService(retrofit: Retrofit):APIService{
        return retrofit.create(APIService::class.java)
    }

}