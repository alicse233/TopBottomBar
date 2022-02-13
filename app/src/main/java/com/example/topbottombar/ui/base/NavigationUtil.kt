package com.example.topbottombar.ui.base

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun Navigation(
    modifier: Modifier = Modifier,
    navHostController: NavHostController,
    onDestinationChangedListener: NavController.OnDestinationChangedListener
) {
    NavHost(
        navHostController,
        startDestination = HOME,
        modifier = modifier
    ) {
        composable(HOME) {
            HomeScreen(navController = navHostController)
        }
        composable(NOTIFICATION) {
            NotificationScreen()
        }
        composable(PROFILE) {
            ProfileScreen()
        }
        composable(DETAIL) {
            DetailScreen()
        }
    }

    navHostController.addOnDestinationChangedListener { controller, destination, arguments ->
        onDestinationChangedListener.onDestinationChanged(controller, destination, arguments)
    }
}

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        TextButton(onClick = { navController.navigate(DETAIL) }) {

            Text(text = "Home screen")
        }
    }
}

@Composable
fun NotificationScreen() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Notifications screen")
    }
}

@Composable
fun ProfileScreen() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Profile screen")
    }
}

@Composable
fun DetailScreen() {
    Box(
        Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Details screen")
    }
}