package com.example.project1.data.remote

import com.google.gson.annotations.SerializedName

data class CandleResponse(
    @SerializedName("c")
    val closePrices: List<Double>,
    @SerializedName("t")
    val timestamps: List<Long>
)
