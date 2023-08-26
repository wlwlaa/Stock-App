package com.example.project1.data.remote

import com.google.gson.annotations.SerializedName

data class Lookup(
    @SerializedName("description")
    val description: String,
    @SerializedName("symbol")
    val symbol: String,
    @SerializedName("type")
    val type: String
)
