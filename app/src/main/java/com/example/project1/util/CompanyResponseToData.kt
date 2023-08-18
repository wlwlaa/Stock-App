package com.example.project1.util

import com.example.project1.data.remote.CompanyProfileResponse
import com.example.project1.viewmodels.company_profile.CompanyProfileData

fun convertCompanyProfile(response: CompanyProfileResponse?) : CompanyProfileData {
    return if (response != null) {
        CompanyProfileData(
            country = response.country,
            currency = response.currency,
            exchange = response.exchange,
            name = response.name,
            ticker = response.ticker,
            ipo = response.ipo,
            marketCapitalization = response.marketCapitalization,
            shareOutstanding = response.shareOutstanding,
            logo = response.logo,
            weburl = response.weburl,
            industry = response.industry
        )
    } else getEmptyProfile()
}