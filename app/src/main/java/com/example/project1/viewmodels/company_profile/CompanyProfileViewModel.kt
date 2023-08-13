package com.example.project1.viewmodels.company_profile

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.api_client.MyApiClient
import com.example.project1.util.convertCompanyProfile
import com.example.project1.util.getCurrentUnixTimestamp
import kotlinx.coroutines.launch

class CompanyProfileViewModel(private val symbol: String): ViewModel() {
    var state by mutableStateOf(CompanyProfileState())
    private val _currentTime = getCurrentUnixTimestamp()

    init {
        state = state.copy(
            symbol = symbol,
            isLoading = true
        )

        viewModelScope.launch {
            try {
                val response = MyApiClient.finnhubService.getStockCandles(symbol = symbol, from = _currentTime - 604800, to = _currentTime)
                state = if (response.isSuccessful) {
                    state.copy(
                        candles = response.body()?.closePrices,
                        timestamps = response.body()?.timestamps,
                    )
                } else {
                    state.copy(
                        errorMessage = "Ошибка ${response.message()}",
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    errorMessage = e.message,
                )
            }
        }
        viewModelScope.launch {
            try {
                val response = MyApiClient.finnhubService.getCompanyProfile(symbol)

                state = if (response.isSuccessful) {
                    state.copy(
                        companyProfile = convertCompanyProfile(response.body()),
                    )
                } else {
                    state.copy(
                        errorMessage = "Не удалось загрузить данные. ${response.message()}",
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    errorMessage = "Ошибка ${e.message}",
                )
            }
        }
        state = state.copy(isLoading = false)
    }
}