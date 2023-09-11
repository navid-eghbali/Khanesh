package khanesh.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import kotlin.time.Duration.Companion.seconds
import kotlin.time.toJavaDuration

internal actual fun getPlatformHttpClient(json: Json): HttpClient = HttpClient(OkHttp) {

    install(ContentNegotiation) {
        json(json = json)
    }

    install(Logging) {
        logger = Logger.DEFAULT
        level = LogLevel.ALL
    }

    engine {
        config {
            connectTimeout(30.seconds.toJavaDuration())
            readTimeout(30.seconds.toJavaDuration())
            retryOnConnectionFailure(true)
        }
    }
}
