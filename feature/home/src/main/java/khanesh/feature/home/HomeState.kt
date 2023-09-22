package khanesh.feature.home

import khanesh.shared.core.model.Promotion

sealed interface HomeState {

    data object Loading : HomeState

    data class Error(val message: String) : HomeState

    data class Success(
        val genres: List<String>,
        val promotions: List<Promotion>,
    ) : HomeState
}
