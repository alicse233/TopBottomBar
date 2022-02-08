package com.example.topbottombar.app_top_bar_utils

import com.example.topbottombar.ui.base.HOME
import com.example.topbottombar.ui.base.NOTIFICATION
import com.example.topbottombar.ui.base.PROFILE

object TopBarHelper {

    fun getTopBarData(route: String?): AppTopBarData {
        when (route) {

            HOME -> {
                return AppTopBarData(
                    title = HOME,
                    shouldShow = true
                )
            }

            NOTIFICATION -> {
                return AppTopBarData(
                    title = NOTIFICATION
                )
            }

            PROFILE -> {
                return AppTopBarData(
                    title = PROFILE,
                    shouldShowBackBtn = true
                )
            }

            else -> {
                return AppTopBarData(
                    shouldShow = false
                )
            }
        }
    }
}