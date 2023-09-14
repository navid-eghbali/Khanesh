package khanesh.feature.home.usecase

import khanesh.shared.core.result.Failure
import khanesh.shared.core.result.Result
import khanesh.shared.network.NetworkClient
import javax.inject.Inject

class GetGenresUseCase @Inject constructor(
    private val networkClient: NetworkClient,
) {

    suspend operator fun invoke(): Result<List<String>, Failure> =
        networkClient.genres()
}
