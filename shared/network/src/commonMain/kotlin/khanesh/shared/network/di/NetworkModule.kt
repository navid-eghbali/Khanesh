package khanesh.shared.network.di

import khanesh.shared.network.NetworkClient
import khanesh.shared.network.getPlatformHttpClient
import kotlinx.serialization.json.Json
import org.kodein.di.DI
import org.kodein.di.bindSingleton

val networkModule: DI.Module = DI.Module(name = "NetworkModule") {

    bindSingleton {
        val json = Json {
            ignoreUnknownKeys = true
            encodeDefaults = true
        }
        NetworkClient(getPlatformHttpClient(json))
    }
}
