package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import com.example.project1.presentation.company_screens.CompanyScreen
import com.example.project1.ui.theme.Project1Theme
import com.example.project1.viewmodels.company_profile.CompanyProfileViewModel

class CompanyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedData = intent.getStringExtra("dataKey")

        setContent {
            Project1Theme {
                if (receivedData != null) {
                    CompanyScreen(CompanyProfileViewModel(receivedData))
                }
                IconButton(onClick = { this.finish() }) {
                    Icon(imageVector = Icons.Filled.KeyboardArrowLeft, contentDescription = "Назад")
                }
            }
        }
    }
}
