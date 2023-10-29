package com.example.project1.models.settings

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.project1.data.local.StockDatabase
import com.example.project1.presentation.sign_in.SignInResult
import com.example.project1.presentation.sign_in.SignInState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class SettingsViewModel(context: Context): ViewModel() {
    private val _db = StockDatabase.builtDatabase(context).stockDao()

    private val _signInState = MutableStateFlow(SignInState())
    val signInState = _signInState.asStateFlow()

    fun onSignInResult(result: SignInResult) {
        _signInState.update { it.copy(
            isSignInSuccessful = result.data != null,
            signInError = result.errorMessage
        ) }
    }

    fun resetState() {
        _signInState.update { SignInState() }
    }

    fun clearDatabase() {
        viewModelScope.launch {
            _db.deleteAllStocks()
        }
    }
}