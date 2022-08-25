package com.mytest.example.data.repository.dataSource

import com.mytest.example.data.model.APIResponse
import retrofit2.Response

interface CovidRemoteDataSource {
    suspend fun getCovidData():Response<APIResponse>
}