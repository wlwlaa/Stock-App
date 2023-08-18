package com.example.project1.presentation.navigation_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.project1.ui.theme.Project1Theme


@Composable
fun SettingsScreen() {
    var isDarkTheme by remember { mutableStateOf(false) }


    Project1Theme(isDarkTheme) {
         Surface {
            Column {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 12.dp, vertical = 10.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = "Настройки",
                        fontSize = 30.sp
                    )
                }
                Divider(
                    Modifier
                        .fillMaxWidth()
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.width(12.dp))

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(12.dp)
                ) {
                    Switch(checked = isDarkTheme, onCheckedChange = { isDarkTheme = it })
                    Spacer(modifier = Modifier.width(120.dp))
                    Text(text = "Темная тема")

                }
            }
        }
    }
}


@Preview
@Composable
fun SettingsTest(){
    SettingsScreen()
}