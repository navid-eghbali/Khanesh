package khanesh.shared.storage

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

actual class DriverFactory {

    actual fun createDriver(): SqlDriver = NativeSqliteDriver(
        schema = Storage.Schema,
        name = "khanesh-storage.db",
    )
}
