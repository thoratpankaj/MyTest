package com.mytest.example.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mytest.example.domain.usecase.repository.GetCovidDataUseCase

class CovidViewModelFactory(
    private val app: Application,
    private val getCovidDataUseCase: GetCovidDataUseCase
):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return CovidViewModel(
            app,
            getCovidDataUseCase
        ) as T
    }
}