package khanesh.shared.data.books

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import khanesh.shared.core.model.Book
import khanesh.shared.core.result.Result
import khanesh.shared.network.NetworkClient

class BooksPagingSource(
    private val networkClient: NetworkClient,
    private val genre: String,
) : PagingSource<Int, Book>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Book> = try {
        val nextPageNumber = params.key ?: 0
        val response = networkClient.books(
            offset = nextPageNumber,
            genres = genre
        )
        when (response) {
            is Result.Success -> PagingSourceLoadResultPage(
                data = response.data.results,
                prevKey = null,
                nextKey = if (response.data.results.isEmpty() || response.data.next == null) null else nextPageNumber + 20
            ) as PagingSourceLoadResult<Int, Book>

            is Result.Error -> PagingSourceLoadResultError<Int, Book>(
                Exception("Error happened!")
            ) as PagingSourceLoadResult<Int, Book>
        }
    } catch (e: Exception) {
        PagingSourceLoadResultError<Int, Book>(e) as PagingSourceLoadResult<Int, Book>
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? = null
}
