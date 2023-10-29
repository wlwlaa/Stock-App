package com.example.project1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.example.project1.models.company_profile.CompanyProfileViewModel
import com.example.project1.presentation.company_screen.CompanyScreen
import com.example.project1.ui.theme.Project1Theme

class CompanyActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val receivedData = intent.getStringExtra("dataKey")

        setContent {
            Project1Theme {
                if (receivedData != null) {
                    CompanyScreen(CompanyProfileViewModel(receivedData, LocalContext.current))
                }
                IconButton(onClick = { this.finish() }, modifier = Modifier.padding(7.dp)) {
                    Surface {
                        Icon(
                            imageVector = Icons.Filled.KeyboardArrowLeft,
                            contentDescription = "Назад"
                        )
                    }
                }
            }
        }
    }
}
