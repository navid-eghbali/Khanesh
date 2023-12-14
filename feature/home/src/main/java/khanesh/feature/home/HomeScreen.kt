package khanesh.feature.home

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer
import khanesh.core.ui.resources.R
import khanesh.feature.home.data.HomeBookShort
import khanesh.feature.home.data.toHomeBookShorts
import khanesh.feature.home.data.toHomePromotions
import khanesh.shared.core.model.BookShort
import khanesh.shared.core.model.Promotion
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf

private val BOOK_ITEM_WIDTH = 150.dp

@Composable
fun HomeScreen(
    onAllGenresClicked: () -> Unit,
    onGenreClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    HomeUi(
        state = state,
        onAllGenresClicked = onAllGenresClicked,
        onGenreClicked = onGenreClicked,
        modifier = modifier
    )
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
private fun HomeUi(
    state: HomeState,
    onAllGenresClicked: () -> Unit,
    onGenreClicked: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.fillMaxSize()) {
        CenterAlignedTopAppBar(
            title = { Text(text = stringResource(id = R.string.home)) }
        )
        Box(modifier = Modifier.fillMaxSize()) {
            when (state) {
                is HomeState.Loading -> LoadingUi(modifier = Modifier.fillMaxSize())
                is HomeState.Error -> {}
                is HomeState.Success -> {
                    LazyColumn(
                        contentPadding = PaddingValues(top = 8.dp, bottom = 88.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        state.promotions.forEach { promotion ->
                            stickyHeader { HeaderItem(title = promotion.title) }
                            item { BooksSlider(books = promotion.items) }
                        }
                        if (state.genres.isNotEmpty()) {
                            stickyHeader {
                                HeaderItem(
                                    title = stringResource(id = R.string.categories),
                                    showArrow = true,
                                    modifier = Modifier.clickable { onAllGenresClicked() }
                                )
                            }
                            items(state.genres) {
                                GenreItem(
                                    genre = it,
                                    onGenreClicked = onGenreClicked
                                )
                            }
                            item {
                                GenreItem(
                                    genre = stringResource(id = R.string.all_genres),
                                    onGenreClicked = { onAllGenresClicked() }
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
fun HeaderItem(
    title: String,
    modifier: Modifier = Modifier,
    placeholderModifier: Modifier = Modifier,
    showArrow: Boolean = false,
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background)
            .padding(16.dp)
    ) {
        if (showArrow) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.primary,
                modifier = placeholderModifier
            )
            Spacer(modifier = Modifier.width(16.dp))
        }
        Text(
            text = title,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Right,
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier
                .fillMaxWidth()
                .then(placeholderModifier)
        )
    }
}

@Composable
fun GenreItem(
    genre: String,
    onGenreClicked: (String) -> Unit,
    modifier: Modifier = Modifier,
) {
    Surface(
        shape = MaterialTheme.shapes.medium,
        color = MaterialTheme.colorScheme.surfaceVariant,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 4.dp)
            .height(64.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .clickable { onGenreClicked(genre) }
                .padding(16.dp)
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowLeft,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSurface,
            )
            Text(
                text = genre,
                textAlign = TextAlign.Right,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.fillMaxWidth()
            )
        }
    }
}

@Composable
fun BooksSlider(
    books: ImmutableList<HomeBookShort>,
    modifier: Modifier = Modifier,
    placeholderModifier: Modifier = Modifier,
) {
    val width = LocalDensity.current.run { BOOK_ITEM_WIDTH.roundToPx() }
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        reverseLayout = true,
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        items(books, key = { it.id }) {
            BookItem(
                book = it,
                width = width,
                placeholderModifier = placeholderModifier
            )
        }
    }
}

@Composable
fun BookItem(
    book: HomeBookShort,
    width: Int,
    modifier: Modifier = Modifier,
    placeholderModifier: Modifier = Modifier,
) {
    Column(modifier = modifier
        .width(BOOK_ITEM_WIDTH)
        .wrapContentHeight()
        .clickable { }
        .padding(8.dp)
        .then(placeholderModifier)
    ) {
        Surface(
            shape = MaterialTheme.shapes.medium,
            shadowElevation = 2.dp,
            modifier = Modifier.wrapContentSize()
        ) {
            AsyncImage(
                model = book.coverUrl(book.coverImage, width, width),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(BOOK_ITEM_WIDTH),
            )
        }
        Text(
            text = book.title,
            color = MaterialTheme.colorScheme.onSurface,
            textAlign = TextAlign.Right,
            maxLines = 2,
            style = MaterialTheme.typography.bodyMedium,
            modifier = modifier.fillMaxWidth()
        )
        Text(
            text = book.compiler,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Right,
            maxLines = 1,
            style = MaterialTheme.typography.bodySmall,
            modifier = modifier.fillMaxWidth()
        )
        /*book.voiceActor?.let { voice ->
            Text(
                text = voice,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                textAlign = TextAlign.Right,
                maxLines = 1,
                style = MaterialTheme.typography.bodySmall,
                modifier = modifier.fillMaxWidth()
            )
        }*/
        book.averageRate?.let { rate ->
            Row(
                horizontalArrangement = Arrangement.Absolute.Right,
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier.fillMaxWidth()
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier.size(12.dp)
                )
                Spacer(modifier = Modifier.width(4.dp))
                Text(
                    text = "$rate",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    style = MaterialTheme.typography.bodySmall,
                )
            }
        }
    }
}

@Composable
fun RetryItem(
    onRetry: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Button(
            onClick = onRetry,
            modifier = Modifier
                .width(192.dp)
                .align(Alignment.Center)
        ) {
            Text(text = stringResource(id = R.string.retry))
            Spacer(modifier = Modifier.width(8.dp))
            Icon(imageVector = Icons.Filled.Refresh, contentDescription = null)
        }
    }
}

@Composable
fun LoadingUi(
    modifier: Modifier = Modifier
) {
    val placeholderModifier = Modifier.placeholder(
        visible = true,
        color = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.6f),
        highlight = PlaceholderHighlight.shimmer()
    )
    Column(
        modifier = modifier
            .verticalScroll(rememberScrollState())
            .padding(vertical = 8.dp)
    ) {
        previewPromotions.forEach { promotion ->
            HeaderItem(
                title = "",
                placeholderModifier = placeholderModifier
            )
            BooksSlider(
                books = promotion.items.toHomeBookShorts(),
                placeholderModifier = placeholderModifier
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewHeaderItem() {
    HeaderItem(title = "تازه‌ها")
}

@Preview(showBackground = true)
@Composable
fun PreviewGenreItem() {
    GenreItem(
        genre = "رمان",
        onGenreClicked = {}
    )
}

@Preview(showBackground = true)
@Composable
fun PreviewBooksSlider() {
    BooksSlider(books = previewBooks.toHomeBookShorts())
}

@Preview(showBackground = true)
@Composable
fun PreviewRetryItem() {
    RetryItem(onRetry = {})
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeUi() {
    HomeUi(
        state = HomeState.Success(
            genres = persistentListOf(),
            promotions = previewPromotions.toHomePromotions(),
        ),
        onAllGenresClicked = {},
        onGenreClicked = {},
    )
}

private val previewBooks: List<BookShort> = listOf(
    BookShort(
        1,
        "آسیا در برابر غرب",
        "داریوش شایگان",
        "بیتا نریمانی",
        "5bd673d82d104a3a804fbb39fe6394f8",
        false,
        null,
        null,
        0,
        false,
        "کتاب-صوتی-ملتهایی-بدون-ملیگرایی"
    ),
    BookShort(
        2,
        "شب‌های روشن",
        "فیودر داستایفسکی",
        "شرگان انورزاده",
        "dc9684a5249e43d88fbb38dae111edc6",
        false,
        null,
        null,
        0,
        false,
        "کتاب-صوتی-روز-آخر-مدرسه-از-مجموعه-مدرسهی-پرماجرا"
    ),
    BookShort(
        3,
        "علیه افسردگی ملی",
        "ژولیا کریستوا",
        "متین بختی",
        "63e0ec7c52e74882a55d5257a470930a",
        false,
        null,
        null,
        0,
        false,
        "کتاب-صوتی-علیه-افسردگی-ملی"
    )
)

private val previewPromotions: List<Promotion> = listOf(
    Promotion(
        title = "تازه‌ها",
        items = previewBooks
    ),
    Promotion(
        title = "تازه‌ها",
        items = previewBooks
    ),
)
