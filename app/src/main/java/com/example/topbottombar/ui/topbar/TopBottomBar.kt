package com.example.topbottombar.ui.topbar

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.topbottombar.app_top_bar_utils.AppTopBarData
import com.example.topbottombar.ui.base.AppGlobalState
import com.example.topbottombar.ui.base.DETAIL

@OptIn(ExperimentalAnimationApi::class, androidx.compose.material.ExperimentalMaterialApi::class)
@Composable
fun TopBar(
    topBarData: AppTopBarData = AppTopBarData(shouldShow = false),
    appGlobalState: AppGlobalState
) {
    AnimatedVisibility(visible = topBarData.shouldShow) {
        TopAppBar(
            title = {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(4.dp)
                ) {

                    TextButton(onClick = { }) {

                        Text(
                            text = topBarData.title,
                            style = MaterialTheme.typography.h6,
                            textAlign = TextAlign.Center,
                            color = Color.White
                        )
                    }

                    Spacer(modifier = Modifier.weight(1f))

                    if (topBarData.showCartIcon) {
                        Column(
                            modifier = Modifier
                                .align(Alignment.CenterVertically)
                                .padding(end = 12.dp)
                        ) {
                            BadgeBox(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                badgeContent = {
                                    Text(
                                        text = topBarData.counter.toString(),
                                        color = Color.White,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            ) {
                                Icon(
                                    imageVector = Icons.Default.ShoppingCart,
                                    contentDescription = null,
                                )
                            }
                        }
                    }
                }
            },
            navigationIcon = {
                if (topBarData.shouldShowBackBtn) {
                    IconButton(onClick = { appGlobalState.upPress() }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = null,

                            )
                    }

                }
            },

            )
    }
}