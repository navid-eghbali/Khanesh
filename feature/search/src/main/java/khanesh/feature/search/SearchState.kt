package khanesh.feature.search

import androidx.compose.runtime.Immutable

@Immutable
internal data class SearchState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val query: String = "",
) {

    val showClearButton = query.isNotEmpty()
}
