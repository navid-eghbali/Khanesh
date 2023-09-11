package khanesh.shared.network

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.ServerResponseException
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpStatusCode
import io.ktor.http.encodedPath
import io.ktor.http.takeFrom
import khanesh.shared.core.result.Failure
import khanesh.shared.core.result.Result

class NetworkClient(
    private val client: HttpClient,
) {

    suspend fun genres(): Result<List<String>, Failure> = tryMapToResult {
        client.get {
            url {
                takeFrom("https://api.vavkhan.com")
                encodedPath = "/book/genres"
            }
        }
    }

    private suspend inline fun <reified T> tryMapToResult(
        httpCall: suspend () -> HttpResponse,
    ): Result<T, Failure> = try {
        val response = httpCall()
        mapToResult(response)
    } catch (e: Exception) {
        Result.Error(Failure.UnknownError(e))
    }

    private suspend inline fun <reified T> mapToResult(response: HttpResponse): Result<T, Failure> {
        return when (response.status.value) {
            in HttpStatusCode.OK.value..HttpStatusCode.MultipleChoices.value ->
                Result.Success(response.body())

            else -> Result.Error(
                Failure.ApiError(
                    code = response.status.value,
                    message = response.status.description,
                    throwable = ServerResponseException(response, response.bodyAsText())
                )
            )
        }
    }
}
