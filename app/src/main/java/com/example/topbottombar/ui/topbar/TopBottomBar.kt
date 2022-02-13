package com.example.topbottombar.ui.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.topbottombar.R
import com.example.topbottombar.app_top_bar_utils.AppTopBarData
import com.example.topbottombar.ui.base.AppGlobalState
import com.example.topbottombar.ui.theme.TopBottomBarTheme
import com.google.accompanist.insets.LocalWindowInsets
import com.google.accompanist.insets.ProvideWindowInsets
import com.google.accompanist.insets.rememberInsetsPaddingValues

val GEN_GRADIENT =
    Brush.horizontalGradient(listOf(Color(0xFF0A8F7F), Color(0xFF003387)))

val TRANSPARENT_GRADIENT =
    Brush.horizontalGradient(listOf(Color(0xFFFFFF), Color(0xFFFFFF)))

@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterialApi::class)
@Composable
fun TopBar(
    topBarData: AppTopBarData = AppTopBarData(shouldShow = false),
    appGlobalState: AppGlobalState
) {
    AnimatedVisibility(visible = topBarData.shouldShow) {

        ProvideWindowInsets {
            TopBottomBarTheme {
                TopAppBar(
                    modifier = Modifier.background(
                        brush = if (topBarData.type == 0) GEN_GRADIENT else TRANSPARENT_GRADIENT,
                    ),
                    contentPadding = rememberInsetsPaddingValues(
                        insets = LocalWindowInsets.current.statusBars,
                        applyStart = true,
                        applyTop = true,
                        applyEnd = true,
                    ),
                    content = {
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(4.dp)
                                .background(Color.Transparent)
                        ) {
                            if (topBarData.shouldShowBackBtn) {
                                IconButton(onClick = { appGlobalState.upPress() }) {
                                    Icon(
                                        imageVector = Icons.Default.ArrowBack,
                                        contentDescription = null,
                                        tint = MaterialTheme.colors.onBackground
                                    )
                                }
                            }

                            TextButton(onClick = { }) {
                                Text(
                                    text = topBarData.title,
                                    style = MaterialTheme.typography.h6,
                                    textAlign = TextAlign.Center,
                                    color = Color.Black
                                )
                            }

                            Spacer(modifier = Modifier.weight(1f))

                            if (topBarData.showCartIcon) {
                                Column(
                                    modifier = Modifier
                                        .align(Alignment.CenterVertically)
                                        .padding(end = 12.dp)
                                ) {
                                    BadgedBox(
                                        modifier = Modifier
                                            .align(Alignment.CenterHorizontally),
                                        content = {
                                            Column(
                                            ) {
                                                Text(
                                                    text = topBarData.counter.toString(),
                                                    color = Color.Black,
                                                    textAlign = TextAlign.Center
                                                )
                                                Icon(
                                                    imageVector = Icons.Default.ShoppingCart,
                                                    contentDescription = null,
                                                )
                                            }
                                        },
                                        badge = {

                                        }
                                    )
                                }
                            }
                        }
                    },
                    backgroundColor = Color.Transparent,
                    elevation = 0.dp
                )
            }
        }
    }
}