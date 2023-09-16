package khanesh.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.feature.home.usecase.GetGenresUseCase
import khanesh.feature.home.usecase.GetPromotionsUseCase
import khanesh.shared.core.result.Failure
import khanesh.shared.core.result.Result
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getGenresUseCase: GetGenresUseCase,
    private val getPromotionsUseCase: GetPromotionsUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState>
        get() = _state

    init {
        getGenresUseCase()
            .onEach { println(it) }
            .launchIn(viewModelScope)
        viewModelScope.launch {
            when (val result = getPromotionsUseCase()) {
                is Result.Success -> _state.update { HomeState.Success(promotions = result.data) }
                is Result.Error -> when (val error = result.error) {
                    is Failure.ApiError -> _state.update { HomeState.Error(error.message) }
                    is Failure.NetworkError -> _state.update { HomeState.Error("خطای شبکه") }
                    is Failure.UnknownError -> _state.update { HomeState.Error("خطای نامشخص") }
                }
            }
        }
    }
}
