package com.mytest.example.data.model


import com.google.gson.annotations.SerializedName

data class APIResponse(
    @SerializedName("data")
    val data: List<Data>
)