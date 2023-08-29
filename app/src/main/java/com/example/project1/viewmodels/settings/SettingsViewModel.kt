package com.example.project1.viewmodels.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.data.local.StockDatabase
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context): ViewModel() {
    private val _db = StockDatabase.builtDatabase(context).stockDao()

    fun clearDatabase() {
        viewModelScope.launch {
            _db.deleteAllStocks()
        }
    }
}