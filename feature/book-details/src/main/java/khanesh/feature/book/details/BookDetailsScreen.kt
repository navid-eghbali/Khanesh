package khanesh.feature.book.details

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
internal fun BookDetailsScreen(
    modifier: Modifier = Modifier,
    viewModel: BookDetailsViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    BookDetailsUi(state, modifier)
}

@Composable
private fun BookDetailsUi(
    state: BookDetailsUiState,
    modifier: Modifier = Modifier
) {
    Box(modifier = modifier.fillMaxSize()) {
        when (state) {
            is BookDetailsUiState.Loading -> {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }

            is BookDetailsUiState.Success -> {
                Text(
                    text = "Showing details of ${state.bookId}",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}
