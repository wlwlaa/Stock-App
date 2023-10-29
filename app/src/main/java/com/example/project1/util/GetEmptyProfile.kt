package com.example.project1.util

import com.example.project1.models.company_profile.CompanyProfileData

fun getEmptyProfile() : CompanyProfileData {
    return  CompanyProfileData(
        country = "",
        currency = "",
        exchange = "",
        name = "",
        ticker = "",
        ipo = "",
        industry = "",
        marketCapitalization = 0.toFloat(),
        shareOutstanding = 0.toFloat(),
        logo = "",
        weburl = ""
    )
}