package khanesh.feature.explore

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.shared.data.genres.GenresRepository
import kotlinx.collections.immutable.toPersistentList
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class ExploreViewModel @Inject constructor(
    private val genresRepository: GenresRepository,
) : ViewModel() {

    private val _state = MutableStateFlow(ExploreState())
    val state = _state.asStateFlow()

    init {
        genresRepository.observeGenres()
            .onEach { genres ->
                _state.update { state ->
                    state.copy(genres = genres.map { it.title }.toPersistentList())
                }
            }
            .launchIn(viewModelScope)
    }
}
