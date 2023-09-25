package khanesh.feature.search

data class SearchState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val query: String = "",
) {

    val showClearButton = query.isNotEmpty()
}
