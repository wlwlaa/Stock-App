package com.example.project1.viewmodels.home

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.data.local.Stock
import com.example.project1.data.local.StockDatabase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


class HomeScreenViewModel(context: Context): ViewModel() {
    private var _stockData = MutableStateFlow<List<Stock>>(emptyList())
    val stockData = _stockData.asStateFlow()

    init {
        viewModelScope.launch {
            StockDatabase.builtDatabase(context).stockDao().getAll().collect {result ->
                _stockData.value = result
            }
        }
    }
}