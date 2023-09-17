package khanesh.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.shared.feature.home.repository.HomeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState>
        get() = _state

    init {
        viewModelScope.launch { homeRepository.syncGenres() }
        viewModelScope.launch { homeRepository.syncPromotions() }
        combine(
            homeRepository.observeGenres(),
            homeRepository.observePromotions()
        ) { genres, promotions ->
            _state.update {
                HomeState.Success(
                    categories = genres.map { it.title },
                    promotions = promotions,
                )
            }
        }.launchIn(viewModelScope)
    }
}
