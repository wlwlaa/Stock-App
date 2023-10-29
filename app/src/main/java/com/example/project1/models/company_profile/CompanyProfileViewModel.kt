package com.example.project1.models.company_profile

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.api_client.MyApiClient
import com.example.project1.data.local.Stock
import com.example.project1.data.local.StockDatabase
import com.example.project1.util.convertCompanyProfile
import com.example.project1.util.getCurrentUnixTimestamp
import com.example.project1.util.getStockChartPoints
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class CompanyProfileViewModel (
    private val symbol: String,
    context: Context
): ViewModel() {
    private var _state = MutableStateFlow(CompanyProfileState())
    var state = _state.asStateFlow()
    private val _currentTime = getCurrentUnixTimestamp()
    private val _stockDao = StockDatabase.builtDatabase(context).stockDao()

    init {
        getData()
    }

    fun addToDatabase() {
        viewModelScope.launch {
            _stockDao.addStock(
                Stock(
                    symbol = symbol,
                    description = _state.value.companyProfile?.name ?: "",
                    type = _state.value.companyProfile?.country ?: ""
                )
            )
            _state.value = _state.value.copy(
                inDatabase = true
            )
        }
    }

    fun deleteFromDatabase() {
        viewModelScope.launch {
            _stockDao.deleteStock(
                Stock(
                    symbol = symbol,
                    description = _state.value.companyProfile?.name ?: "",
                    type = _state.value.companyProfile?.country ?: ""
                )
            )
            _state.value = _state.value.copy(
                inDatabase = false
            )
        }
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

                if (_stockDao.getBySymbol(symbol) != null) {
                    _state.value = _state.value.copy(
                        inDatabase = true
                    )
                } else {
                    _state.value = _state.value.copy(
                        inDatabase = false
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