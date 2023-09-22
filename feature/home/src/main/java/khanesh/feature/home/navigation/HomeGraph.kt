package khanesh.feature.home.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khanesh.feature.home.HomeScreen

fun NavGraphBuilder.homeGraph(
    onAllGenresClicked: () -> Unit,
    onGenreClicked: (String) -> Unit,
) {
    composable(
        route = HomeRouter.route,
        arguments = HomeRouter.arguments,
        deepLinks = HomeRouter.deepLinks,
    ) {
        HomeScreen(
            onAllGenresClicked = onAllGenresClicked,
            onGenreClicked = onGenreClicked,
        )
    }
}
