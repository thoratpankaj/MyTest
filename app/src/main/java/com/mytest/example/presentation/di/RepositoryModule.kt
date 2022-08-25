package com.mytest.example.presentation.di

import com.mytest.example.data.repository.CovidRepositoryImpl
import com.mytest.example.data.repository.dataSource.CovidRemoteDataSource
import com.mytest.example.domain.usecase.repository.CovidRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {
    @Singleton
    @Provides
    fun provideCovidRepository(
        covidRemoteDataSource: CovidRemoteDataSource
    ):CovidRepository{
        return CovidRepositoryImpl(covidRemoteDataSource)
    }
}