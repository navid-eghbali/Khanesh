package khanesh.feature.book.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.feature.book.details.navigation.BookDetailsArgs
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
internal class BookDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val args: BookDetailsArgs = BookDetailsArgs(savedStateHandle)

    private val _state: MutableStateFlow<BookDetailsUiState> =
        MutableStateFlow(BookDetailsUiState.Loading)
    val state: StateFlow<BookDetailsUiState>
        get() = _state

    init {
        _state.update { BookDetailsUiState.Success(args.bookId) }
    }
}
