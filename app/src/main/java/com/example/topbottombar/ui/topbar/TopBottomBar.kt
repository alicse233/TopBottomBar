package com.example.topbottombar.ui.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun topBar(
    navController: NavController,
    shouldShow: Boolean = true,
    title: String = "",
    shouldShowBackBtn: Boolean = false
) {
    when (navController.currentBackStackEntry?.destination?.route) {

    }

    AnimatedVisibility(visible = shouldShow) {
        TopAppBar(title = {
            Text(
                text = "MyComposeApp",
                style = MaterialTheme.typography.h6,
                textAlign = TextAlign.Center
            )
        })
    }
}