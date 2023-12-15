package khanesh.feature.search

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDirection
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import khanesh.core.ui.resources.R

@Composable
internal fun SearchScreen(
    modifier: Modifier = Modifier,
    viewModel: SearchViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    SearchUi(
        state = state,
        onSearch = viewModel::onSearch,
        onQueryChanged = viewModel::onQueryChanged,
        onClearClicked = viewModel::onClearClicked,
        modifier = modifier,
    )
}

@Composable
private fun SearchUi(
    state: SearchState,
    onSearch: () -> Unit,
    onQueryChanged: (String) -> Unit,
    onClearClicked: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(modifier = modifier.fillMaxSize()) {
        TextField(
            value = state.query,
            onValueChange = onQueryChanged,
            textStyle = MaterialTheme.typography.bodyLarge.copy(textDirection = TextDirection.ContentOrRtl),
            placeholder = {
                Text(
                    text = stringResource(id = R.string.explore),
                    textAlign = TextAlign.Right,
                    modifier = Modifier.fillMaxWidth()
                )
            },
            leadingIcon = {
                if (state.showClearButton) {
                    IconButton(onClick = onClearClicked) {
                        Icon(imageVector = Icons.Filled.Clear, contentDescription = null)
                    }
                }
            },
            trailingIcon = { Icon(imageVector = Icons.Filled.Search, contentDescription = null) },
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            keyboardActions = KeyboardActions(onSearch = { onSearch() }),
            singleLine = true,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent
            ),
            modifier = Modifier.fillMaxWidth()
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when {
                state.isLoading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                state.errorMessage?.isNotEmpty() == true -> Unit
                state.query.isEmpty() -> RecentSearchesUi()
                else -> Unit
            }
        }
    }
}

@Composable
private fun RecentSearchesUi(
    modifier: Modifier = Modifier,
) {
    LazyColumn {

    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewRecentSearchesUi() {
    RecentSearchesUi()
}

@Preview(showBackground = true)
@Composable
private fun PreviewSearchUi() {
    SearchUi(
        state = SearchState(),
        onSearch = {},
        onQueryChanged = {},
        onClearClicked = {},
    )
}
