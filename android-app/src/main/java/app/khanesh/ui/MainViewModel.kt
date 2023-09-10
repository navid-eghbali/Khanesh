package app.khanesh.ui

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.feature.home.navigation.HomeRouter
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(
        MainUiState(
            currentDestination = HomeRouter.route
        )
    )
    val state: StateFlow<MainUiState>
        get() = _state

    fun onNavigationBarItemClicked(route: String) {
        _state.update { _state.value.copy(currentDestination = route) }
    }
}
