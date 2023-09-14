package khanesh.feature.home.usecase

import khanesh.shared.core.model.Promotion
import khanesh.shared.core.result.Failure
import khanesh.shared.core.result.Result
import khanesh.shared.network.NetworkClient
import javax.inject.Inject

class GetPromotionsUseCase @Inject constructor(
    private val networkClient: NetworkClient,
) {

    suspend operator fun invoke(): Result<List<Promotion>, Failure> =
        networkClient.promotions()
}
