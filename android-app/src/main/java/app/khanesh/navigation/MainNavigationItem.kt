package app.khanesh.navigation

import androidx.annotation.DrawableRes
import androidx.compose.runtime.Immutable
import khanesh.core.ui.resources.R
import khanesh.feature.explore.navigation.ExploreRouter
import khanesh.feature.home.navigation.HomeRouter
import khanesh.feature.library.navigation.LibraryRouter

@Immutable
data class MainNavigationItem(
    val route: String,
    val textId: Int,
    @DrawableRes val selectedIcon: Int,
    @DrawableRes val unselectedIcon: Int,
)

internal fun navigationItems(): List<MainNavigationItem> = listOf(
    MainNavigationItem(
        route = HomeRouter.route,
        textId = R.string.home,
        selectedIcon = R.drawable.ic_filled_home_24,
        unselectedIcon = R.drawable.ic_outline_home_24,
    ),
    MainNavigationItem(
        route = ExploreRouter.route,
        textId = R.string.explore,
        selectedIcon = R.drawable.ic_filled_explore_24,
        unselectedIcon = R.drawable.ic_outline_explore_24,
    ),
    MainNavigationItem(
        route = LibraryRouter.route,
        textId = R.string.library,
        selectedIcon = R.drawable.ic_filled_library_24,
        unselectedIcon = R.drawable.ic_outline_library_24,
    ),
)
