package app.khanesh.ui

import app.khanesh.navigation.MainNavigationItem
import app.khanesh.navigation.navigationItems

data class MainUiState(
    val currentDestination: String = "",
    val mainDestinations: List<MainNavigationItem> = navigationItems(),
)
