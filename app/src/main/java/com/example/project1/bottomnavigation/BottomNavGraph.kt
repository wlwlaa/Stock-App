package com.example.project1.bottomnavigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.project1.presentation.navigation_screens.HomeScreen
import com.example.project1.presentation.navigation_screens.SearchScreen
import com.example.project1.presentation.navigation_screens.SettingsScreen
import com.example.project1.viewmodels.home.HomeScreenViewModel
import com.example.project1.viewmodels.search.SearchViewModel

@Composable
fun BottomNavGraph (navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = BottomMenuItem.Home.route
    ) {
        composable(route = BottomMenuItem.Home.route) {
            HomeScreen(HomeScreenViewModel(LocalContext.current))
        }
        composable(route = BottomMenuItem.Search.route) {
            SearchScreen(SearchViewModel())
        }
        composable(route = BottomMenuItem.Settings.route) {
            SettingsScreen()
        }
    }

}