package khanesh.feature.explore.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khanesh.feature.explore.ExploreScreen

fun NavGraphBuilder.exploreGraph(
    onSearchClicked: () -> Unit,
    onGenreClicked: (String) -> Unit,
) {
    composable(
        route = ExploreRouter.route,
        arguments = ExploreRouter.arguments,
        deepLinks = ExploreRouter.deepLinks,
    ) {
        ExploreScreen(
            onSearchClicked = onSearchClicked,
            onGenreClicked = onGenreClicked,
        )
    }
}
