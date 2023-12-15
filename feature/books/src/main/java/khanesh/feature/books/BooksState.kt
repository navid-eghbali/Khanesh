package khanesh.feature.books

import androidx.compose.runtime.Immutable
import app.cash.paging.PagingData
import khanesh.shared.core.model.Book
import kotlinx.coroutines.flow.Flow

@Immutable
internal sealed interface BooksState {

    @Immutable
    data object Loading : BooksState

    @Immutable
    data class Success(
        val genre: String,
        val booksPager: Flow<PagingData<Book>>,
    ) : BooksState
}
