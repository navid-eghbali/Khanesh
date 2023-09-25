package khanesh.feature.explore

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import khanesh.core.ui.designsystem.AppColorPalette
import khanesh.core.ui.resources.R

@Composable
fun ExploreScreen(
    onSearchClicked: () -> Unit,
    onGenreClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: ExploreViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    ExploreUi(
        state = state,
        onSearchClicked = onSearchClicked,
        onGenreClicked = onGenreClicked,
        modifier = modifier,
    )
}

@Composable
private fun ExploreUi(
    state: ExploreState,
    onSearchClicked: () -> Unit,
    onGenreClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    val colors = AppColorPalette.shuffled()
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier.fillMaxSize()
    ) {
        item(span = { GridItemSpan(2) }) { SearchItem(onSearchClicked) }
        itemsIndexed(state.genres) { index, item ->
            GenreItem(
                genre = item,
                color = colors[index % colors.size],
                onGenreClicked = onGenreClicked
            )
        }
    }
}

@Composable
fun SearchItem(
    onSearchClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        tonalElevation = 3.dp,
        shadowElevation = 3.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(64.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .clickable { onSearchClicked() }
                .padding(horizontal = 8.dp)
        ) {
            Text(
                text = stringResource(id = R.string.explore),
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )
            Icon(imageVector = Icons.Filled.Search, contentDescription = null)
        }
    }
}

@Composable
fun GenreItem(
    genre: String,
    color: Color,
    onGenreClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = color,
        contentColor = Color.White,
        shadowElevation = 1.dp,
        modifier = modifier
            .height(96.dp)
            .clickable { onGenreClicked(genre) }
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = genre,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewSearchItem() {
    SearchItem(onSearchClicked = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewGenreItem() {
    GenreItem(
        genre = "مجموعه داستان",
        color = Color.Blue,
        onGenreClicked = {},
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewExploreScreen() {
    ExploreUi(
        state = ExploreState(
            genres = listOf(
                "رمان",
                "مجموعه داستان",
                "موفقیت",
                "شعر",
                "عاشقانه",
                "فانتزی",
                "کودک",
            )
        ),
        onSearchClicked = {},
        onGenreClicked = {},
    )
}
