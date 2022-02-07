package com.example.topbottombar.ui.base

import android.widget.Space
import androidx.compose.foundation.layout.*
import androidx.compose.material.BottomNavigation
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.topbottombar.ui.bottom_nav.BottomNav
import com.example.topbottombar.ui.bottom_nav.BottomNavItem
import com.example.topbottombar.ui.theme.TopBottomBarTheme
import com.example.topbottombar.ui.topbar.topBar

@Composable
fun BaseApp(

) {

    val bottomItems = listOf(
        BottomNavItem(
            "home", Icons.Default.Home,
            "home", "home"
        ),
        BottomNavItem(
            "notification", Icons.Default.Notifications,
            "notification", "notification",
            counter = 87
        ),
        BottomNavItem(
            "profile", Icons.Default.Home,
            "profile", "profile"
        )
    )

    TopBottomBarTheme {
        val navController = rememberNavController()
        val backStackEntry = navController.currentBackStackEntryAsState()

        var title by remember {
            mutableStateOf(navController.currentDestination ?: navController.currentDestination?.displayName)
        }

        Scaffold(

            topBar = {
                topBar(
                    navController
                )
            },

            bottomBar = {

                BottomNav(
                    items = bottomItems,

                    navController = navController,

                    onItemClick = { navController.navigate(it.route) }
                )
            }
        ) {

            Navigation(navHostController = navController)
        }
    }

}