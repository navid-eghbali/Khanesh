package khanesh.feature.explore

data class ExploreState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val genres: List<String> = emptyList(),
)
