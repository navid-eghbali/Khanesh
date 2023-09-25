package khanesh.feature.search.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khanesh.feature.search.SearchScreen

fun NavGraphBuilder.searchGraph() {
    composable(
        route = SearchRouter.route,
        arguments = SearchRouter.arguments,
        deepLinks = SearchRouter.deepLinks,
    ) {
        SearchScreen()
    }
}
