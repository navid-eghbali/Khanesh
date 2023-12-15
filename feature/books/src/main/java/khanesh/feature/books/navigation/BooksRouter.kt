package khanesh.feature.books.navigation

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

internal data class BooksArgs(
    val genre: String,
) {

    constructor(arguments: Bundle) : this(
        genre = arguments.getString("genre", ""),
    )

    constructor(savedStateHandle: SavedStateHandle) : this(
        genre = checkNotNull(savedStateHandle["genre"]),
    )
}

object BooksRouter {

    private const val PATH = "books"

    const val route = "$PATH/{genre}"

    val arguments: List<NamedNavArgument> = listOf(
        navArgument("genre") {
            type = NavType.StringType
        }
    )

    val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "khanesh://$route" }
    )

    fun NavController.navigateToBooks(genre: String) {
        navigate("$PATH/$genre") {
            launchSingleTop = true
        }
    }
}
