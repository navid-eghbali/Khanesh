package khanesh.feature.book.details

import androidx.compose.runtime.Immutable

@Immutable
internal sealed interface BookDetailsUiState {

    @Immutable
    data object Loading : BookDetailsUiState

    @Immutable
    data class Success(
        val bookId: Long
    ) : BookDetailsUiState
}
