package khanesh.shared.storage.daos

import app.cash.sqldelight.coroutines.asFlow
import app.cash.sqldelight.coroutines.mapToList
import khanesh.shared.base.AppCoroutineDispatchers
import khanesh.shared.storage.Promotions
import khanesh.shared.storage.Storage
import kotlinx.coroutines.flow.Flow

class PromotionsDao(
    private val storage: Storage,
    private val dispatchers: AppCoroutineDispatchers,
) {

    fun insert(promotion: Promotions): Long {
        storage.promotionsQueries.insert(
            id = promotion.id,
            promotionTitle = promotion.promotionTitle,
            bookId = promotion.bookId,
            title = promotion.title,
            author = promotion.author,
            coverImage = promotion.coverImage,
            averageRate = promotion.averageRate,
            rateCount = promotion.rateCount,
        )
        return storage.promotionsQueries.lastInsertRowId().executeAsOne()
    }

    fun insertAll(promotions: List<Promotions>) {
        storage.transaction {
            promotions.forEach { promotion ->
                storage.promotionsQueries.insert(
                    id = promotion.id,
                    promotionTitle = promotion.promotionTitle,
                    bookId = promotion.bookId,
                    title = promotion.title,
                    author = promotion.author,
                    coverImage = promotion.coverImage,
                    averageRate = promotion.averageRate,
                    rateCount = promotion.rateCount,
                )
            }
        }
    }

    fun getPromotions(): Flow<List<Promotions>> = storage.promotionsQueries
        .selectAll()
        .asFlow()
        .mapToList(dispatchers.io)

    fun delete() {
        storage.promotionsQueries.deleteAll()
    }
}
