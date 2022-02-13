package com.example.topbottombar.ui.bottom_nav

import androidx.compose.foundation.layout.Column
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomNav(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemClick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    BottomNavigation(
        modifier = modifier,
        backgroundColor = Color.DarkGray,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                selectedContentColor = Color.Green,
                unselectedContentColor = Color.Gray,
                onClick = { onItemClick(item) },
                icon = {
                    Column(
                        modifier = Modifier.align(CenterVertically)
                    ) {
                        if (item.counter != null) {
                            Column {
                            Badge(
                                modifier = Modifier.align(CenterHorizontally),
                                content = {
                                        Text(
                                            text = item.counter.toString(),
                                            color = Color.White,
                                            textAlign = TextAlign.Center
                                        )
                                },
                            )
                            Icon(imageVector = item.icon, contentDescription = null)
                        }
                        } else {
                            Icon(imageVector = item.icon, contentDescription = null)
                        }

                        if (selected) {
                            Text(text = item.name, textAlign = TextAlign.Center, fontSize = 10.sp)
                        }
                    }
                }
            )
        }
    }
}
