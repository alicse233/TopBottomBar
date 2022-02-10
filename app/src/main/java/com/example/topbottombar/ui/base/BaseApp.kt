package com.example.topbottombar.ui.base

import android.content.res.Resources
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.*
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.topbottombar.app_top_bar_utils.AppTopBarData
import com.example.topbottombar.app_top_bar_utils.TopBarHelper
import com.example.topbottombar.ui.bottom_nav.BottomNav
import com.example.topbottombar.ui.bottom_nav.BottomNavItem
import com.example.topbottombar.ui.theme.TopBottomBarTheme
import com.example.topbottombar.ui.topbar.TopBar
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect


val bottomTabs = listOf(
    BottomNavItem(
        "home", Icons.Default.Home,
        "home", route = HOME
    ),
    BottomNavItem(
        "notification", Icons.Default.Notifications,
        "notification", route = NOTIFICATION,
        counter = 87
    ),
    BottomNavItem(
        "profile", Icons.Default.Home,
        "profile", route = PROFILE
    )
)

@Composable
fun BaseApp(

) {

    TopBottomBarTheme {

        val navController = rememberNavController()

        var topBarData by remember {
            mutableStateOf(AppTopBarData(shouldShow = false))
        }

        val appState = rememberAppState(
            navController = navController,
        )

        Scaffold(

            topBar = {
                if (topBarData.shouldShow) {
                    TopBar(
                        topBarData = topBarData,
                        appGlobalState = appState
                    )
                }
            },

            bottomBar = {
                if (appState.shouldShowBottomBar) {
                    BottomNav(
                        items = bottomTabs,

                        navController = navController,

                        onItemClick = {
                            navController.navigate(it.route) {
                                launchSingleTop = true
                                restoreState = true
                                // Pop up backstack to the first destination and save state. This makes going back
                                // to the start destination when pressing back in any other bottom tab.
//                popUpTo(findStartDestination(navController.graph).id) {
//                    saveState = true }
                            }
                        }
                    )
                }
            },
            scaffoldState = appState.scaffoldState
        ) {
            Navigation(navHostController = navController,
                onDestinationChangedListener = { controller, destination, arguments ->
                    if (destination.route.equals(DETAIL)) {
                        topBarData = TopBarHelper.getTopBarData(
                            destination.route ?: "",
                            DETAIL,
                            appState.resources,
                            12
                        )
                    } else {
                        topBarData = TopBarHelper.getTopBarData(
                            destination.route ?: "",
                            resources = appState.resources
                        )
                    }
                })
        }
    }

}

/**
 * Remembers and creates an instance of [JetsnackAppState]
 */
@Composable
fun rememberAppState(
    scaffoldState: ScaffoldState = rememberScaffoldState(),
    navController: NavHostController = rememberNavController(),
    snackbarManager: SnackBarManager = SnackBarManager,
    resources: Resources = resources(),
    coroutineScope: CoroutineScope = rememberCoroutineScope()
) =
    remember(scaffoldState, navController, snackbarManager, resources, coroutineScope) {
        AppGlobalState(scaffoldState, navController, snackbarManager, resources, coroutineScope)
    }

class AppGlobalState(
    val scaffoldState: ScaffoldState,
    private val navController: NavHostController,
    private val snackbarManager: SnackBarManager,
    val resources: Resources,
    coroutineScope: CoroutineScope
) {
    // Process snackbars coming from SnackbarManager
    init {
        coroutineScope.launch {
            snackbarManager.messages.collect { currentMessages ->
                if (currentMessages.isNotEmpty()) {
                    val message = currentMessages[0]
                    val text = resources.getText(message.messageId)

                    // Display the snackbar on the screen. `showSnackbar` is a function
                    // that suspends until the snackbar disappears from the screen
                    scaffoldState.snackbarHostState.showSnackbar(text.toString())
                    // Once the snackbar is gone or dismissed, notify the SnackbarManager
                    snackbarManager.setMessageShown(message.id)
                }
            }
        }
    }

    // ----------------------------------------------------------
    // BottomBar state source of truth
    // ----------------------------------------------------------

    private val bottomBarRoutes = bottomTabs.map { it.route }

    // Reading this attribute will cause recompositions when the bottom bar needs shown, or not.
    // Not all routes need to show the bottom bar.
    val shouldShowBottomBar: Boolean
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination?.route in
                bottomBarRoutes

    // ----------------------------------------------------------
    // Navigation state source of truth
    // ----------------------------------------------------------

    val currentRoute: String?
        get() = navController.currentDestination?.route

    fun upPress() {
        navController.navigateUp()
    }

}

/**
 * A composable function that returns the [Resources]. It will be recomposed when `Configuration`
 * gets updated.
 */
@Composable
@ReadOnlyComposable
private fun resources(): Resources {
    LocalConfiguration.current
    return LocalContext.current.resources
}