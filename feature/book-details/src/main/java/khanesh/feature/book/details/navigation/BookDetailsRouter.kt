package khanesh.feature.book.details.navigation

import android.os.Bundle
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavController
import androidx.navigation.NavDeepLink
import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

data class BookDetailsArgs(
    val bookId: Long
) {
    constructor(arguments: Bundle) : this(
        bookId = arguments.getLong("bookId"),
    )

    constructor(savedStateHandle: SavedStateHandle) : this(
        bookId = checkNotNull(savedStateHandle["bookId"]),
    )
}

object BookDetailsRouter {

    private const val PATH = "book-details"

    const val route = "$PATH/{bookId}"

    val arguments: List<NamedNavArgument> = listOf(
        navArgument("bookId") {
            type = NavType.LongType
        }
    )

    val deepLinks: List<NavDeepLink> = listOf(
        navDeepLink { uriPattern = "khanesh://$route" }
    )

    fun NavController.navigateToBookDetails(bookId: Long) {
        navigate("$PATH/$bookId") {
            launchSingleTop = true
        }
    }
}
