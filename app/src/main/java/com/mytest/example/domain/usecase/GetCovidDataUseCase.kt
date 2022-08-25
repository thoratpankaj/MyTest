package com.mytest.example.domain.usecase.repository

import com.mytest.example.data.model.APIResponse
import com.mytest.example.data.util.Resource

class GetCovidDataUseCase(private val covidRepository: CovidRepository) {

    suspend fun execute():Resource<APIResponse>{
        return covidRepository.getCovidData()
    }
}