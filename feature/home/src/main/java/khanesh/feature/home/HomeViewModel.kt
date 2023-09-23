package khanesh.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.shared.core.result.Failure
import khanesh.shared.core.result.Result
import khanesh.shared.data.genres.GenresRepository
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
    private val genresRepository: GenresRepository,
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<HomeState>(HomeState.Loading)
    val state: StateFlow<HomeState>
        get() = _state

    init {
        viewModelScope.launch {
            when (val response = genresRepository.syncGenres()) {
                is Result.Error -> when (val error = response.error) {
                    is Failure.ApiError -> {}
                    is Failure.NetworkError -> {}
                    is Failure.UnknownError -> {}
                }

                is Result.Success -> Unit
            }
        }
        viewModelScope.launch {
            when (val response = homeRepository.syncPromotions()) {
                is Result.Error -> when (val error = response.error) {
                    is Failure.ApiError -> {}
                    is Failure.NetworkError -> {}
                    is Failure.UnknownError -> {}
                }

                is Result.Success -> Unit
            }
        }
        combine(
            genresRepository.observeGenres(),
            homeRepository.observePromotions()
        ) { genres, promotions ->
            _state.update {
                HomeState.Success(
                    genres = genres.take(5).map { it.title },
                    promotions = promotions,
                )
            }
        }.launchIn(viewModelScope)
    }
}
