package app.khanesh.ui

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.khanesh.navigation.MainNavigationItem
import khanesh.core.ui.designsystem.AppTheme
import khanesh.feature.book.details.navigation.bookDetailsGraph
import khanesh.feature.explore.navigation.exploreGraph
import khanesh.feature.genres.navigation.GenresRouter.navigateToGenres
import khanesh.feature.genres.navigation.genresGraph
import khanesh.feature.home.navigation.HomeRouter
import khanesh.feature.home.navigation.homeGraph
import khanesh.feature.library.navigation.libraryGraph
import khanesh.feature.search.navigation.SearchRouter.navigateToSearch
import khanesh.feature.search.navigation.searchGraph

@Composable
fun MainScreen(
    state: MainState,
    navController: NavHostController = rememberNavController(),
) {
    val showBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in state.mainDestinations.map { it.route }
    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                val navBackStackEntry by navController.currentBackStackEntryAsState()
                MainNavigationBar(
                    navBackStackEntry = navBackStackEntry,
                    navigationItems = state.mainDestinations,
                    onItemSelected = {
                        navController.navigate(it) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    },
                    modifier = Modifier.fillMaxWidth()
                )
            }
        },
        containerColor = MaterialTheme.colorScheme.background,
        contentColor = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier.fillMaxSize(),
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = HomeRouter.route,
            modifier = Modifier.padding(padding)
        ) {
            homeGraph(
                onAllGenresClicked = { navController.navigateToGenres() },
                onGenreClicked = {},
            )
            exploreGraph(
                onSearchClicked = { navController.navigateToSearch() },
                onGenreClicked = {},
            )
            libraryGraph()
            bookDetailsGraph()
            genresGraph(
                navigateUp = navController::popBackStack,
                onGenreClicked = {},
            )
            searchGraph()
        }
    }
}

@Composable
fun MainNavigationBar(
    navBackStackEntry: NavBackStackEntry?,
    navigationItems: List<MainNavigationItem>,
    onItemSelected: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    NavigationBar(
        contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
        windowInsets = WindowInsets.navigationBars,
        modifier = modifier
    ) {
        val currentDestination = navBackStackEntry?.destination
        navigationItems.forEach { item ->
            val selected = currentDestination?.hierarchy?.any { it.route == item.route } == true
            NavigationBarItem(
                selected = selected,
                onClick = { onItemSelected(item.route) },
                icon = {
                    Icon(
                        painterResource(id = if (selected) item.selectedIcon else item.unselectedIcon),
                        stringResource(id = item.textId),
                        tint = MaterialTheme.colorScheme.primary,
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.textId),
                        color = MaterialTheme.colorScheme.primary
                    )
                },
            )
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    AppTheme {
        MainScreen(state = MainState())
    }
}
