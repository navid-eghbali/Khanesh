package app.khanesh.ui

import app.khanesh.navigation.MainNavigationItem
import app.khanesh.navigation.navigationItems

data class MainState(
    val mainDestinations: List<MainNavigationItem> = navigationItems(),
)
