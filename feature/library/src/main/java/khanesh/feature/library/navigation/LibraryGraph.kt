package khanesh.feature.library.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khanesh.feature.library.LibraryScreen

fun NavGraphBuilder.libraryGraph() {
    composable(
        route = LibraryRouter.route,
        arguments = LibraryRouter.arguments,
        deepLinks = LibraryRouter.deepLinks,
    ) {
        LibraryScreen()
    }
}
