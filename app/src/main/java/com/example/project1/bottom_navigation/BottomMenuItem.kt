package com.example.project1.bottom_navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomMenuItem(
    val route: String,
    val label: String,
    val icon: ImageVector) {
    object Home : BottomMenuItem(
        route = "homescreen",
        label = "Главная",
        icon = Icons.Filled.Home
    )
    object Search : BottomMenuItem(
        route = "searchscreen",
        label = "Поиск",
        icon = Icons.Filled.Search
    )
    object Settings : BottomMenuItem(
        route = "settingsscreen",
        label = "Настройки",
        icon = Icons.Filled.Settings
    )
}
