package khanesh.feature.book.details

sealed interface BookDetailsUiState {

    data object Loading : BookDetailsUiState

    data class Success(
        val bookId: Long
    ) : BookDetailsUiState
}
