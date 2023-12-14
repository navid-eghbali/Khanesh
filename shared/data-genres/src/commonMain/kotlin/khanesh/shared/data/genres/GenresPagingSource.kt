package khanesh.shared.data.genres

import app.cash.paging.PagingSource
import app.cash.paging.PagingSourceLoadParams
import app.cash.paging.PagingSourceLoadResult
import app.cash.paging.PagingSourceLoadResultError
import app.cash.paging.PagingSourceLoadResultPage
import app.cash.paging.PagingState
import khanesh.shared.core.model.Book
import khanesh.shared.core.result.Result
import khanesh.shared.network.NetworkClient

class GenresPagingSource(
    private val networkClient: NetworkClient,
    private val genre: String,
) : PagingSource<Int, Book>() {

    override suspend fun load(
        params: PagingSourceLoadParams<Int>
    ): PagingSourceLoadResult<Int, Book> {
        return try {
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
    }

    override fun getRefreshKey(state: PagingState<Int, Book>): Int? = null
}
