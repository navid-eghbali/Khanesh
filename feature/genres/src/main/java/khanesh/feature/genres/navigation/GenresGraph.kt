package khanesh.feature.genres.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khanesh.feature.genres.GenresScreen

fun NavGraphBuilder.genresGraph(
    navigateUp: () -> Unit,
    onGenreClicked: (String) -> Unit,
) {
    composable(
        route = GenresRouter.route,
        arguments = GenresRouter.arguments,
        deepLinks = GenresRouter.deepLinks,
    ) {
        GenresScreen(
            navigateUp = navigateUp,
            onGenreClicked = onGenreClicked,
        )
    }
}
