package com.example.project1.bottomnavigation

import androidx.compose.foundation.shape.AbsoluteRoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.project1.ui.theme.Project1Theme

@Composable
fun BottomNavigation(navController: NavController) {
    val items: List<BottomMenuItem> = listOf(
        BottomMenuItem.Home,
        BottomMenuItem.Search,
        BottomMenuItem.Settings)

    Project1Theme {
        NavigationBar(
            modifier = Modifier
                .clip(shape = AbsoluteRoundedCornerShape(topLeftPercent = 20, topRightPercent = 20))
        ) {
            val backStackEntry by navController.currentBackStackEntryAsState()
            val currentRoute = backStackEntry?.destination?.route
            items.forEach { bottomMenuItem ->
                NavigationBarItem(
                    selected = currentRoute == bottomMenuItem.route,
                    onClick = { navController.navigate(bottomMenuItem.route) },
                    icon = { Icon(bottomMenuItem.icon, contentDescription = bottomMenuItem.label) },
                    label = { Text(bottomMenuItem.label) },
                    alwaysShowLabel = false
                )
            }
        }
    }
}