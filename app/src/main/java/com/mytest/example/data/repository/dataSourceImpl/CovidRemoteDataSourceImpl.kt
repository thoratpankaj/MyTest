package com.mytest.example.data.repository.dataSourceImpl

import com.mytest.example.data.api.APIService
import com.mytest.example.data.model.APIResponse
import com.mytest.example.data.repository.dataSource.CovidRemoteDataSource
import retrofit2.Response

class CovidRemoteDataSourceImpl(
    private val apiService: APIService
) : CovidRemoteDataSource {
    override suspend fun getCovidData(): Response<APIResponse> {

        return apiService.getCovidData()
    }

}