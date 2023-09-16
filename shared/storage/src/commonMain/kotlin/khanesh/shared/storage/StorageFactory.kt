package khanesh.shared.storage

import app.cash.sqldelight.db.SqlDriver

class StorageFactory(
    private val driver: SqlDriver,
) {

    fun build(): Storage = Storage(driver)
}
