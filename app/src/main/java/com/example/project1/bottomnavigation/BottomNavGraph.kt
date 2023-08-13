package com.example.project1.bottomnavigation

import com.example.project1.viewmodels.search.SearchViewModel
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project1.presentation.navigation_screens.HomeScreen
import com.example.project1.presentation.navigation_screens.SearchScreen
import com.example.project1.presentation.navigation_screens.SettingsScreen

@Composable
fun BottomNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomMenuItem.Home.route
    ) {
        composable(route = BottomMenuItem.Home.route) {
            HomeScreen()
        }
        composable(route = BottomMenuItem.Search.route) {
            SearchScreen(SearchViewModel())
        }
        composable(route = BottomMenuItem.Settings.route) {
            SettingsScreen()
        }
    }

}