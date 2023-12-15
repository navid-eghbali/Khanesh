package khanesh.feature.books

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import app.cash.paging.LoadStateLoading
import app.cash.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.emptyFlow

@Composable
internal fun BooksScreen(
    navigateUp: () -> Unit,
    onBookClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: BooksViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    BooksUi(
        state = state,
        navigateUp = navigateUp,
        onBookClicked = onBookClicked,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun BooksUi(
    state: BooksState,
    navigateUp: () -> Unit,
    onBookClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    when (state) {
        is BooksState.Loading -> Box(modifier = modifier.fillMaxSize()) {
            CircularProgressIndicator()
        }

        is BooksState.Success -> {
            val items = state.booksPager.collectAsLazyPagingItems()
            Scaffold(
                topBar = {
                    CenterAlignedTopAppBar(
                        title = { Text(text = state.genre) },
                        navigationIcon = {
                            IconButton(onClick = navigateUp) {
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                                    contentDescription = null,
                                )
                            }
                        }
                    )
                },
                modifier = modifier,
            ) { padding ->
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                ) {
                    if (items.loadState.refresh == LoadStateLoading) {
                        item {
                            CircularProgressIndicator(
                                strokeWidth = 2.dp,
                                strokeCap = StrokeCap.Round,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .wrapContentWidth(Alignment.CenterHorizontally)
                            )
                        }
                    }

                    items(count = items.itemCount) { index ->
                        ListItem(
                            title = items[index]?.title ?: "...",
                            onItemClicked = onBookClicked
                        )
                    }

                    if (items.loadState.append == LoadStateLoading) {
                        item {
                            Box(modifier = Modifier.fillMaxSize()) {
                                CircularProgressIndicator(
                                    strokeCap = StrokeCap.Round,
                                    strokeWidth = 2.dp,
                                    modifier = Modifier
                                        .size(16.dp)
                                        .align(Alignment.Center)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ListItem(
    title: String,
    onItemClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { onItemClicked(title) }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
        )
        Text(
            text = title,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.bodyLarge,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewListItem() {
    ListItem(
        title = "مجموعه داستان",
        onItemClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewBooksUi() {
    BooksUi(
        state = BooksState.Success("رمان", emptyFlow()),
        navigateUp = {},
        onBookClicked = {},
    )
}
