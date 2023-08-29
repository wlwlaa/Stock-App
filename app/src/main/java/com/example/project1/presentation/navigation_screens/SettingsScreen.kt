package com.example.project1.presentation.navigation_screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.viewmodels.settings.SettingsViewModel


@Composable
fun SettingsScreen(viewModel: SettingsViewModel) {
    val context = LocalContext.current

     Surface {
        Column {
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 12.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Настройки",
                    fontSize = 30.sp
                )
            }
            Divider(Modifier.padding(7.dp))

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        viewModel.clearDatabase()
                        Toast
                            .makeText(context, "База данных очищена!", Toast.LENGTH_SHORT)
                            .show()
                    }
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Очистить базу данных")
            }
            Divider(Modifier.padding(7.dp))

            Spacer(modifier = Modifier.height(12.dp))

        }
    }
}