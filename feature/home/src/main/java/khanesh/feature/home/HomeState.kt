package khanesh.feature.home

import androidx.compose.runtime.Immutable
import khanesh.feature.home.data.HomePromotion
import kotlinx.collections.immutable.ImmutableList

@Immutable
sealed interface HomeState {

    @Immutable
    data object Loading : HomeState

    @Immutable
    data class Error(val message: String) : HomeState

    @Immutable
    data class Success(
        val genres: ImmutableList<String>,
        val promotions: ImmutableList<HomePromotion>,
    ) : HomeState
}
