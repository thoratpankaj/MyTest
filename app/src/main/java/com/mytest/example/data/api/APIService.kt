package com.mytest.example.data.api

import com.mytest.example.data.model.APIResponse
import retrofit2.Response
import retrofit2.http.GET

interface APIService {

    @GET("regions")
    suspend fun getCovidData(): Response<APIResponse>

}