package com.example.project1.viewmodels.company_profile

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.api_client.MyApiClient
import com.example.project1.util.convertCompanyProfile
import com.example.project1.util.getCurrentUnixTimestamp
import com.example.project1.util.getStockChartPoints
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class CompanyProfileViewModel @Inject constructor(private val symbol: String): ViewModel() {
    private var _state = MutableStateFlow(CompanyProfileState())
    var state = _state.asStateFlow()
    private val _currentTime = getCurrentUnixTimestamp()

    init {
        getData()
    }

    private fun getData() {
        viewModelScope.launch {
            try {
                _state.value = _state.value.copy(
                    symbol = symbol
                )

                val stockCandleResponse = async { MyApiClient.finnhubService.getStockCandles(symbol = symbol, from = _currentTime - 777600, to = _currentTime) }.await()
                val companyProfileResponse = async { MyApiClient.finnhubService.getCompanyProfile(symbol) }.await()

                if (stockCandleResponse.isSuccessful) {
                    _state.value = _state.value.copy(
                        candles = getStockChartPoints(stockCandleResponse.body()!!.closePrices, stockCandleResponse.body()!!.timestamps)
                    )
                } else {
                    _state.value = _state.value.copy(
                        candleErrorMessage = stockCandleResponse.message()
                    )
                }
                if (companyProfileResponse.isSuccessful) {
                    _state.value = _state.value.copy(
                        companyProfile = convertCompanyProfile(companyProfileResponse.body())
                    )
                } else {
                    _state.value = _state.value.copy(
                        companyErrorMessage = companyProfileResponse.message()
                    )
                }

            } catch (e: Exception) {
                _state.value = _state.value.copy(
                    companyErrorMessage = e.message,
                    candleErrorMessage = e.message
                )
            }
        }
    }
}