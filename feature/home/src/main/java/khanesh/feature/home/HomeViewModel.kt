package khanesh.feature.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import khanesh.shared.network.NetworkClient
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val networkClient: NetworkClient,
) : ViewModel() {

    init {
        viewModelScope.launch {
            println(networkClient.genres())
        }
    }
}
