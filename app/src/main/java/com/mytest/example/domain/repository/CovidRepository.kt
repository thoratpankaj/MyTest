package com.mytest.example.domain.usecase.repository

import com.mytest.example.data.model.APIResponse
import com.mytest.example.data.repository.CovidRepositoryImpl
import com.mytest.example.data.repository.dataSource.CovidRemoteDataSource
import com.mytest.example.data.util.Resource

interface CovidRepository {
    suspend fun getCovidData(): Resource<APIResponse>
}