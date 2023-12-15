package khanesh.feature.explore

import androidx.compose.runtime.Immutable
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

@Immutable
internal data class ExploreState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val genres: ImmutableList<String> = persistentListOf(),
)
