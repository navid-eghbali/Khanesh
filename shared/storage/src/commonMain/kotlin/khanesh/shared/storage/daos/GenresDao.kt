package khanesh.shared.storage.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import khanesh.shared.storage.Genres
import khanesh.shared.storage.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.flow.Flow

class GenresDao(
    private val storage: Storage,
) {

    fun insert(title: String): Long {
        storage.genresQueries.insert(null, title)
        return storage.genresQueries.lastInsertRowId().executeAsOne()
    }

    fun insertAll(genres: List<String>) {
        storage.transaction {
            genres.forEach { storage.genresQueries.insert(null, it) }
        }
    }

    fun getGenres(): Flow<List<Genres>> = storage.genresQueries
        .selectAll()
        .asFlow()
        .mapToList(Dispatchers.IO)

    fun delete() {
        storage.genresQueries.deleteAll()
    }
}
