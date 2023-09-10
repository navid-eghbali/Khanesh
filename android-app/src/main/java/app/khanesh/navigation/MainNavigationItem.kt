package app.khanesh.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material.icons.rounded.Home
import androidx.compose.material.icons.rounded.List
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.Immutable
import androidx.compose.ui.graphics.vector.ImageVector
import khanesh.core.ui.resources.R
import khanesh.feature.home.navigation.HomeRouter

@Immutable
data class MainNavigationItem(
    val route: String,
    val textId: Int,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

internal fun navigationItems(): List<MainNavigationItem> = listOf(
    MainNavigationItem(
        route = HomeRouter.route,
        textId = R.string.home,
        selectedIcon = Icons.Rounded.Home,
        unselectedIcon = Icons.Outlined.Home,
    ),
    MainNavigationItem(
        route = "explore",
        textId = R.string.explore,
        selectedIcon = Icons.Rounded.Search,
        unselectedIcon = Icons.Outlined.Search,
    ),
    MainNavigationItem(
        route = "library",
        textId = R.string.library,
        selectedIcon = Icons.Rounded.List,
        unselectedIcon = Icons.Outlined.List,
    ),
)
