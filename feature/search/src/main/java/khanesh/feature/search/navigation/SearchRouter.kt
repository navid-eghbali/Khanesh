package khanesh.feature.search.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink

object SearchRouter {

    const val route = "search"

    val arguments: List<NamedNavArgument> = listOf()

    val deepLinks: List<NavDeepLink> = listOf()

    fun NavController.navigateToSearch() {
        navigate(route) {
            launchSingleTop = true
        }
    }
}
