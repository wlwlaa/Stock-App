package com.example.project1.viewmodels.company_profile

data class CompanyProfileState(
    val symbol: String = "",
    var companyProfile: CompanyProfileData? = null,
    var candles: List<Double>? = emptyList(),
    var timestamps: List<Long>? = emptyList(),
    var errorMessage: String? = null,
    var isLoading: Boolean = false
)
