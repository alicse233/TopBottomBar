package com.example.topbottombar.ui.bottom_nav

import androidx.compose.ui.graphics.vector.ImageVector

data class BottomNavItem(
    val title: String,
    val icon: ImageVector,
    val name: String,
    val route: String,
    val counter: Int? = null
)
