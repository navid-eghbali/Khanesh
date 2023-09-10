package khanesh.feature.book.details.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khanesh.feature.book.details.BookDetailsScreen

fun NavGraphBuilder.bookDetailsGraph() {
    composable(
        route = BookDetailsRouter.route,
        arguments = BookDetailsRouter.arguments,
        deepLinks = BookDetailsRouter.deepLinks,
    ) {
        BookDetailsScreen()
    }
}
