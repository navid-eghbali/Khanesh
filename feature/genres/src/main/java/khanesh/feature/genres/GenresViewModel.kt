package khanesh.feature.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.shared.data.genres.GenresRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    genresRepository: GenresRepository,
) : ViewModel() {

    private val _state = MutableStateFlow<GenresState>(GenresState.Loading)
    val state: StateFlow<GenresState>
        get() = _state

    init {
        genresRepository.observeGenres()
            .onEach { genres -> _state.update { GenresState.Success(genres = genres.map { it.title }) } }
            .launchIn(viewModelScope)
    }
}
