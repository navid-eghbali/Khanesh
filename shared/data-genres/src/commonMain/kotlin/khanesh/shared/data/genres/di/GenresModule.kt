package khanesh.shared.data.genres.di

import khanesh.shared.data.genres.GenresRepository
import org.kodein.di.DI
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val genresModule: DI.Module = DI.Module(name = "GenresModule") {

    bindSingleton {
        GenresRepository(
            genresDao = instance(),
            networkClient = instance(),
            transactionRunner = instance(),
        )
    }
}
