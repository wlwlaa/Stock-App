package com.example.project1.presentation.navigation_screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun HomeScreen() {
    Surface {
        Column {
            Box(
                modifier = Modifier
                    .padding(horizontal = 12.dp, vertical = 15.dp)
                    .fillMaxWidth()
            ) {
                Text(
                    text = "Избранное",
                    fontSize = 30.sp
                )
            }
            Divider(Modifier.padding(12.dp))
        }
    }
}


@Preview
@Composable
fun HomeTest() {
    HomeScreen()
}