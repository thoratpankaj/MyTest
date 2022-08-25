package com.mytest.example.presentation.di

import com.mytest.example.data.api.APIService
import com.mytest.example.data.repository.dataSource.CovidRemoteDataSource
import com.mytest.example.data.repository.dataSourceImpl.CovidRemoteDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class RemoteDataModule {
    @Singleton
    @Provides
    fun provideCovidRemoteDataSource(apiService: APIService):CovidRemoteDataSource{
        return CovidRemoteDataSourceImpl(apiService)
    }
}