package com.example.project1.viewmodels.company_profile

import com.example.project1.data.local.IntradayInfo

data class CompanyProfileState(
    val symbol: String = "",
    var companyProfile: CompanyProfileData? = null,
    var candles: List<IntradayInfo> = emptyList(),
    var candleErrorMessage: String? = null,
    var companyErrorMessage: String? = null,
    var inDatabase: Boolean = false
)
