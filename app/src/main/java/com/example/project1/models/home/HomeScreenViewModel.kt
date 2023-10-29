package com.example.project1.models.home

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

    private val _db = StockDatabase.builtDatabase(context).stockDao()

    init {
        viewModelScope.launch {
            _db.getAll().collect {result ->
                _stockData.value = result
            }
        }
    }
}