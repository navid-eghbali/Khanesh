package khanesh.shared.storage

import app.cash.sqldelight.adapter.primitive.FloatColumnAdapter
import app.cash.sqldelight.adapter.primitive.IntColumnAdapter
import app.cash.sqldelight.db.SqlDriver

class StorageFactory(
    private val driver: SqlDriver,
) {

    fun build(): Storage = Storage(
        driver = driver,
        promotionsAdapter = Promotions.Adapter(
            averageRateAdapter = FloatColumnAdapter,
            rateCountAdapter = IntColumnAdapter
        )
    )
}
