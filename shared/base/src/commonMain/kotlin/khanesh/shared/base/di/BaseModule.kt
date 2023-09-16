package khanesh.shared.base.di

import khanesh.shared.base.AppCoroutineDispatchers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val baseModule: DI.Module = DI.Module(name = "BaseModule") {

    bindSingleton {
        AppCoroutineDispatchers(
            io = Dispatchers.IO,
            computation = Dispatchers.Default,
            main = Dispatchers.Main,
        )
    }
}
