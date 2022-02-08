package com.example.topbottombar.app_top_bar_utils

data class AppTopBarData(
    val shouldShow: Boolean = true,
    val title: String = "",
    val shouldShowBackBtn: Boolean = false,
    val showCartIcon: Boolean = false,
    val counter: Int? = null,
)