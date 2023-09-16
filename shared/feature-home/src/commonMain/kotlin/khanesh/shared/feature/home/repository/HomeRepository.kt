package khanesh.shared.feature.home.repository

import khanesh.shared.core.model.Promotion
import khanesh.shared.core.result.Result
import khanesh.shared.feature.home.mapper.toListPromotion
import khanesh.shared.feature.home.mapper.toListPromotions
import khanesh.shared.network.NetworkClient
import khanesh.shared.storage.Genres
import khanesh.shared.storage.daos.GenresDao
import khanesh.shared.storage.daos.PromotionsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HomeRepository(
    private val genresDao: GenresDao,
    private val promotionsDao: PromotionsDao,
    private val networkClient: NetworkClient,
) {

    fun observeGenres(): Flow<List<Genres>> = genresDao.getGenres()

    fun observePromotions(): Flow<List<Promotion>> = promotionsDao.getPromotions()
        .map { it.toListPromotion() }

    suspend fun syncPromotions(clearCache: Boolean = false) {
        if (clearCache) promotionsDao.delete()
        when (val response = networkClient.promotions()) {
            is Result.Success -> {
                promotionsDao.delete()
                promotionsDao.insertAll(response.data.toListPromotions())
            }

            is Result.Error -> Unit
        }
    }

    suspend fun syncGenres() {
        when (val response = networkClient.genres()) {
            is Result.Success -> {
                genresDao.delete()
                genresDao.insertAll(response.data)
            }

            else -> Unit
        }
    }
}
