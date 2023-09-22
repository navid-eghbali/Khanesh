package khanesh.shared.storage.di

import khanesh.shared.storage.StorageFactory
import khanesh.shared.storage.TransactionRunner
import khanesh.shared.storage.daos.GenresDao
import khanesh.shared.storage.daos.PromotionsDao
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val storageModule: DI.Module = DI.Module(name = "StorageModule") {

    bindProvider { StorageFactory(driver = instance()).build() }

    bindProvider { TransactionRunner(storage = instance()) }

    bindSingleton {
        GenresDao(
            storage = instance(),
            dispatchers = instance(),
        )
    }

    bindSingleton {
        PromotionsDao(
            storage = instance(),
            dispatchers = instance(),
        )
    }
}
