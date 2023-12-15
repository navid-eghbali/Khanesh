package khanesh.feature.books

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.feature.books.navigation.BooksArgs
import khanesh.shared.data.books.BooksPagingSource
import khanesh.shared.network.NetworkClient
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class BooksViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    networkClient: NetworkClient,
) : ViewModel() {

    private val args: BooksArgs = BooksArgs(savedStateHandle)
    private val _state = MutableStateFlow<BooksState>(BooksState.Loading)

    val state: StateFlow<BooksState>
        get() = _state

    init {
        _state.update {
            val genre = args.genre
            BooksState.Success(
                genre = genre,
                booksPager = Pager(PagingConfig(pageSize = 20)) {
                    BooksPagingSource(networkClient, genre)
                }.flow.cachedIn(viewModelScope),
            )
        }
    }
}
