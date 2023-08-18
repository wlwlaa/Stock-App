package com.example.project1.api_client

import com.example.project1.data.remote.CandleResponse
import com.example.project1.data.remote.CompanyProfileResponse
import com.example.project1.data.remote.SymbolLookupResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

object MyApiClient {
    private const val BASE_URL = "https://finnhub.io/api/v1/"
    private const val API_KEY = "ciqolu1r01qjff7csko0ciqolu1r01qjff7cskog"
    private const val _stockResolution = "D"

    private val retrofit: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    val finnhubService: FinnhubService by lazy {
        retrofit.create(FinnhubService::class.java)
    }

    interface FinnhubService {
        @GET("stock/candle")
        suspend fun getStockCandles(
            @Query("symbol") symbol: String,
            @Query("resolution") resolution: String = _stockResolution,
            @Query("from") from: Long,
            @Query("to") to: Long,
            @Query("token") token: String = API_KEY
        ): Response<CandleResponse>

        @GET("stock/profile2")
        suspend fun getCompanyProfile(
            @Query("symbol") symbol: String?,
            @Query("isin") isin: String? = null,
            @Query("cusip") cusip: String? = null,
            @Query("token") token: String = API_KEY
        ): Response<CompanyProfileResponse>

        @GET("search")
        suspend fun getSymbolLookup(
            @Query("q") q: String,
            @Query("token") token: String = API_KEY
        ): Response<SymbolLookupResponse>
    }
}