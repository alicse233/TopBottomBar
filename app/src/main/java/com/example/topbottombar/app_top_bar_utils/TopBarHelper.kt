package com.example.topbottombar.app_top_bar_utils

import android.content.res.Resources
import com.example.topbottombar.R
import com.example.topbottombar.ui.base.HOME
import com.example.topbottombar.ui.base.NOTIFICATION
import com.example.topbottombar.ui.base.PROFILE

object TopBarHelper {

    fun getTopBarData(
        route: String?,
        title: String? = null,
        resources: Resources,
        counter: Int? = null
    ): AppTopBarData {
        when (route) {

            HOME -> {
                return AppTopBarData(
                    title = resources.getString(R.string.home),
                    shouldShow = true
                )
            }

            NOTIFICATION -> {
                return AppTopBarData(
                    title = resources.getString(R.string.notifications),
                )
            }

            PROFILE -> {
                return AppTopBarData(
                    title = resources.getString(R.string.profile),
                    shouldShowBackBtn = true
                )
            }

            else -> {
                return AppTopBarData(
                    title = title ?: "",
                    shouldShow = true,
                    shouldShowBackBtn = true,
                    showCartIcon = true,
                    counter = counter
                )
            }
        }
    }
}