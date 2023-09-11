package khanesh.shared.network

import io.ktor.client.HttpClient
import kotlinx.serialization.json.Json

internal expect fun getPlatformHttpClient(json: Json): HttpClient
