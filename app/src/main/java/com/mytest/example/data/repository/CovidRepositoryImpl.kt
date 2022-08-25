package com.mytest.example.data.repository

import com.mytest.example.data.model.APIResponse
import com.mytest.example.data.repository.dataSource.CovidRemoteDataSource
import com.mytest.example.data.util.Resource
import com.mytest.example.domain.usecase.repository.CovidRepository
import retrofit2.Response

class CovidRepositoryImpl(
    private val covidRemoteDataSource: CovidRemoteDataSource
) : CovidRepository {
     override suspend fun getCovidData(): Resource<APIResponse> {

        return responseToResource(covidRemoteDataSource.getCovidData())
    }


    private fun responseToResource(response: Response<APIResponse>): Resource<APIResponse> {
        if (response.isSuccessful) {
            response.body()?.let { result ->
                return Resource.Success(result)
            }
        }
        return Resource.Error(response.message())
    }
}