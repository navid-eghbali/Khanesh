package khanesh.shared.feature.home.di

import khanesh.shared.feature.home.repository.HomeRepository
import khanesh.shared.feature.home.usecase.GetPromotionsUseCase
import org.kodein.di.DI
import org.kodein.di.bindProvider
import org.kodein.di.bindSingleton
import org.kodein.di.instance

val homeModule: DI.Module = DI.Module(name = "HomeModule") {

    bindSingleton {
        HomeRepository(
            genresDao = instance(),
            promotionsDao = instance(),
            networkClient = instance(),
        )
    }

    bindProvider {
        GetPromotionsUseCase(
            networkClient = instance()
        )
    }
}
