package com.example.project1.models.company_profile

data class CompanyProfileData(
    val country: String,
    val currency: String,
    val exchange: String,
    val name: String,
    val ticker: String,
    val ipo: String,
    val marketCapitalization: Float,
    val shareOutstanding: Float,
    val logo: String,
    val weburl: String,
    val industry: String
)
