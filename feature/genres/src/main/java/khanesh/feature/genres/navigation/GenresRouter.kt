package khanesh.feature.genres.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink

object GenresRouter {

    const val route = "genres"

    val arguments: List<NamedNavArgument> = listOf()

    val deepLinks: List<NavDeepLink> = listOf()

    fun NavController.navigateToGenres() {
        navigate("$route") {
            launchSingleTop = true
        }
    }
}
