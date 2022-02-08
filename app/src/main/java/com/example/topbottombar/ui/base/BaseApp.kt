package com.example.topbottombar.ui.base

import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.runtime.*
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.topbottombar.app_top_bar_utils.AppTopBarData
import com.example.topbottombar.app_top_bar_utils.TopBarHelper
import com.example.topbottombar.ui.bottom_nav.BottomNav
import com.example.topbottombar.ui.bottom_nav.BottomNavItem
import com.example.topbottombar.ui.theme.TopBottomBarTheme
import com.example.topbottombar.ui.topbar.topBar
import kotlin.coroutines.EmptyCoroutineContext.get

private val shouldShowBottomBar: Boolean
    @Composable get() = navController
        .currentBackStackEntryAsState().value?.destination?.route in bottomBarRoutes
@Composable
fun BaseApp(

) {

    val bottomTabs = listOf(
        BottomNavItem(
            "home", Icons.Default.Home,
            "home", HOME
        ),
        BottomNavItem(
            "notification", Icons.Default.Notifications,
            "notification", NOTIFICATION,
            counter = 87
        ),
        BottomNavItem(
            "profile", Icons.Default.Home,
            "profile", PROFILE
        )
    )

    val bottomBarRoutes = bottomTabs.map { it.route }

    TopBottomBarTheme {

        val navController = rememberNavController()

        val backStackEntry = navController.currentBackStackEntryAsState()

        var topBarData by remember {
            mutableStateOf(AppTopBarData(shouldShow = false))
        }


        Scaffold(

            topBar = {
                topBar(
                    topBarData = topBarData
                )
            },

            bottomBar = {

                BottomNav(
                    items = bottomTabs,

                    navController = navController,

                    onItemClick = { navController.navigate(it.route) }
                )
            }
        ) {

            Navigation(navHostController = navController,
                onDestinationChangedListener = { controller, destination, arguments ->
                    topBarData = TopBarHelper.getTopBarData(destination.route ?: "")
                })
        }
    }

}