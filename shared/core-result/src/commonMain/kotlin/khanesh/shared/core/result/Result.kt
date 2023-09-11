package khanesh.shared.core.result

sealed class Result<out R, out F> {

    data class Success<out R>(val data: R) : Result<R, Nothing>()

    data class Error<out F>(val error: F) : Result<Nothing, F>()
}

sealed interface Failure {

    data class ApiError(
        val code: Int,
        val message: String,
        val throwable: Throwable
    ) : Failure

    data object NetworkError : Failure

    data class UnknownError(val throwable: Throwable) : Failure
}
