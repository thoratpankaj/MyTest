package com.mytest.example.presentation.di

import android.app.Application
import com.mytest.example.domain.usecase.repository.GetCovidDataUseCase
import com.mytest.example.presentation.viewmodel.CovidViewModelFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
class FactoryModule {
    @Singleton
    @Provides
    fun provideViewModelFactory(
        app: Application,
        getCovidDataUseCase: GetCovidDataUseCase
    ): CovidViewModelFactory {
        return CovidViewModelFactory(app, getCovidDataUseCase)
    }
}