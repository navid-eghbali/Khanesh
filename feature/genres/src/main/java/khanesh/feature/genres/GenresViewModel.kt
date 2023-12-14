package khanesh.feature.genres

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.shared.data.genres.GenresPagingSource
import khanesh.shared.data.genres.GenresRepository
import khanesh.shared.network.NetworkClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class GenresViewModel @Inject constructor(
    genresRepository: GenresRepository,
    networkClient: NetworkClient,
) : ViewModel() {

    private val _state = MutableStateFlow<GenresState>(GenresState.Loading)
    val state: StateFlow<GenresState>
        get() = _state

    val pager = Pager(PagingConfig(pageSize = 20)) {
        GenresPagingSource(networkClient, "رمان")
    }.flow.cachedIn(viewModelScope)

    init {
        genresRepository.observeGenres()
            .onEach { genres -> _state.update { GenresState.Success(genres = genres.map { it.title }) } }
            .launchIn(viewModelScope)
    }
}
