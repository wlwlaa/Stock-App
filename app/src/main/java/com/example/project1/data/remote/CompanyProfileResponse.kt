package com.example.project1.data.remote

import com.google.gson.annotations.SerializedName

data class CompanyProfileResponse(
    @SerializedName("country")
    val country: String,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("exchange")
    val exchange: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("ticker")
    val ticker: String,
    @SerializedName("ipo")
    val ipo: String,
    @SerializedName("marketCapitalization")
    val marketCapitalization: Float,
    @SerializedName("shareOutstanding")
    val shareOutstanding: Float,
    @SerializedName("logo")
    val logo: String,
    @SerializedName("weburl")
    val weburl: String,
    @SerializedName("finnhubIndustry")
    val industry: String
)
