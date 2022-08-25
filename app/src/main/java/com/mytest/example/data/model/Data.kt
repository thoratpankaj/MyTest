package com.mytest.example.data.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("iso")
    val iso: String,
    @SerializedName("name")
    val name: String
)