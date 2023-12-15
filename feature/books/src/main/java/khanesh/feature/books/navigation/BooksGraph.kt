package khanesh.feature.books.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import khanesh.feature.books.BooksScreen

fun NavGraphBuilder.booksGraph(
    navigateUp: () -> Unit,
    onBookClicked: (String) -> Unit,
) {
    composable(
        route = BooksRouter.route,
        arguments = BooksRouter.arguments,
        deepLinks = BooksRouter.deepLinks,
    ) {
        BooksScreen(
            navigateUp = navigateUp,
            onBookClicked = onBookClicked,
        )
    }
}
