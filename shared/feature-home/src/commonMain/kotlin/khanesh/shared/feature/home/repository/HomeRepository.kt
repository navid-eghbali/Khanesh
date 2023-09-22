package khanesh.shared.feature.home.repository

import khanesh.shared.core.model.Promotion
import khanesh.shared.core.result.Failure
import khanesh.shared.core.result.Result
import khanesh.shared.feature.home.mapper.toListPromotion
import khanesh.shared.feature.home.mapper.toListPromotions
import khanesh.shared.network.NetworkClient
import khanesh.shared.storage.Promotions
import khanesh.shared.storage.TransactionRunner
import khanesh.shared.storage.daos.PromotionsDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class HomeRepository(
    private val promotionsDao: PromotionsDao,
    private val networkClient: NetworkClient,
    private val transactionRunner: TransactionRunner,
) {

    fun observePromotions(): Flow<List<Promotion>> = promotionsDao.getPromotions()
        .distinctUntilChanged()
        .map { it.toListPromotion() }

    suspend fun syncPromotions(): Result<List<Promotions>, Failure> {
        return when (val response = networkClient.promotions()) {
            is Result.Success -> {
                val data = response.data.toListPromotions()
                // promotionsDao.delete()
                promotionsDao.insertAll(data)
                Result.Success(data)
            }

            is Result.Error -> Result.Error(response.error)
        }
    }
}
