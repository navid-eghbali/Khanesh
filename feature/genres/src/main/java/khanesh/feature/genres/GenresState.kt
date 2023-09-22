package khanesh.feature.genres

sealed interface GenresState {

    data object Loading : GenresState

    data class Success(
        val genres: List<String>,
    ) : GenresState
}
