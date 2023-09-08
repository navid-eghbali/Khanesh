package app.khanesh.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import app.khanesh.R
import app.khanesh.navigation.MainNavigationItem
import dagger.hilt.android.AndroidEntryPoint
import khanesh.core.ui.designsystem.AppTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            AppTheme {
                val state by viewModel.state.collectAsState()
                MainUi(
                    state = state,
                    onNavigationBarItemClicked = viewModel::onNavigationBarItemClicked
                )
            }
        }
    }
}

@Composable
private fun MainUi(
    state: MainUiState,
    navController: NavHostController = rememberNavController(),
    onNavigationBarItemClicked: (String) -> Unit,
) {
    val showBottomBar =
        navController.currentBackStackEntryAsState().value?.destination?.route in listOf(
            "home",
            "explore",
            "library"
        )
    Scaffold(
        bottomBar = {
            AnimatedVisibility(showBottomBar) {
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
                        onNavigationBarItemClicked(it)
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
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable(route = "home") {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(id = R.string.home),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            composable(route = "explore") {
                Box(modifier = Modifier.fillMaxSize()) {
                    Button(
                        onClick = { navController.navigate(route = "book-details") },
                        modifier = Modifier.align(Alignment.Center)
                    ) {
                        Text(text = stringResource(id = R.string.explore))
                    }
                }
            }
            composable(route = "library") {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = stringResource(id = R.string.library),
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
            composable(route = "book-details") {
                Box(modifier = Modifier.fillMaxSize()) {
                    Text(
                        text = "جزئیات کتاب",
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
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
                        if (selected) item.selectedIcon else item.unselectedIcon,
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
        MainUi(
            state = MainUiState(),
            onNavigationBarItemClicked = {},
        )
    }
}
