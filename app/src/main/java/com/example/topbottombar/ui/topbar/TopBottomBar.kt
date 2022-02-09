package com.example.topbottombar.ui.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.example.topbottombar.app_top_bar_utils.AppTopBarData

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun TopBar(
    topBarData: AppTopBarData = AppTopBarData(shouldShow = false)
) {
    AnimatedVisibility(visible = topBarData.shouldShow) {
        TopAppBar(
            title = {
                Text(
                    text = topBarData.title,
                    style = MaterialTheme.typography.h6,
                    textAlign = TextAlign.Center,
                    color = Color.White
                )
            },
            navigationIcon = {
                if (topBarData.shouldShowBackBtn) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = null)
                }
            }
        )
    }
}