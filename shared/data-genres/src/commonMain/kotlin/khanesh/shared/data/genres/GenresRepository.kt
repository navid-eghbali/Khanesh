package khanesh.shared.data.genres

import khanesh.shared.core.result.Failure
import khanesh.shared.core.result.Result
import khanesh.shared.network.NetworkClient
import khanesh.shared.storage.Genres
import khanesh.shared.storage.TransactionRunner
import khanesh.shared.storage.daos.GenresDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged

class GenresRepository(
    private val genresDao: GenresDao,
    private val networkClient: NetworkClient,
    private val transactionRunner: TransactionRunner,
) {

    fun observeGenres(): Flow<List<Genres>> = genresDao.getGenres()
        .distinctUntilChanged()

    suspend fun syncGenres(): Result<List<String>, Failure> {
        return when (val response = networkClient.genres()) {
            is Result.Success -> {
                val data = response.data
                genresDao.delete()
                genresDao.insertAll(data)
                Result.Success(data)
            }

            is Result.Error -> Result.Error(response.error)
        }
    }
}
