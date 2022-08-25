package com.mytest.example.presentation.di

import com.mytest.example.domain.usecase.repository.CovidRepository
import com.mytest.example.domain.usecase.repository.GetCovidDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class UseCaseModule {

    @Singleton
    @Provides
    fun providesUseCase(
        covidRepository: CovidRepository
    ):GetCovidDataUseCase{
        return GetCovidDataUseCase(covidRepository)
    }
}