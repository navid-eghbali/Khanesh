package khanesh.feature.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor() : ViewModel() {

    private val _state = MutableStateFlow(SearchState())
    val state = _state.asStateFlow()

    fun onQueryChanged(text: String) {
        _state.update { it.copy(query = text) }
    }

    fun onClearClicked() {
        _state.update { it.copy(query = "") }
    }

    fun onSearch() {
        viewModelScope.launch {
            _state.update { it.copy(isLoading = true) }
            val query = _state.value.query
            _state.update { it.copy(isLoading = false) }
        }
    }
}
