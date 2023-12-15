package khanesh.feature.genres

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList

@Immutable
internal sealed interface GenresState {

    @Immutable
    data object Loading : GenresState

    @Immutable
    data class Success(
        val genres: ImmutableList<String>,
    ) : GenresState
}
