package khanesh.feature.home

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
import androidx.compose.foundation.lazy.staggeredgrid.LazyHorizontalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.AssistChipDefaults
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
import androidx.compose.ui.draw.clip
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
import khanesh.shared.core.model.BookShort
import khanesh.shared.core.model.Promotion

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = hiltViewModel(),
) {
    val state by viewModel.state.collectAsState()
    HomeUi(state, modifier)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeUi(
    state: HomeState,
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
                        contentPadding = PaddingValues(vertical = 8.dp),
                        modifier = Modifier.fillMaxSize()
                    ) {
                        item { HeaderItem(title = stringResource(id = R.string.categories)) }
                        item { CategoriesSlider(categories = state.categories) }
                        state.promotions.forEach { promotion ->
                            item { HeaderItem(title = promotion.title) }
                            item { BooksSlider(books = promotion.items) }
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
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .clickable { }
            .padding(16.dp)
    ) {
        Icon(
            imageVector = Icons.Filled.ArrowBack,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.primary,
            modifier = placeholderModifier
        )
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
fun CategoriesSlider(
    categories: List<String>,
    modifier: Modifier = Modifier,
) {
    LazyHorizontalStaggeredGrid(
        rows = StaggeredGridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        reverseLayout = true,
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalItemSpacing = 8.dp,
        modifier = modifier
            .fillMaxWidth()
            .height(88.dp)
    ) {
        items(categories) {
            AssistChip(
                onClick = { },
                label = { Text(text = it, modifier = Modifier.padding(horizontal = 8.dp)) },
                shape = MaterialTheme.shapes.large,
                colors = AssistChipDefaults.assistChipColors(
                    containerColor = MaterialTheme.colorScheme.secondary,
                    labelColor = MaterialTheme.colorScheme.onSecondary,
                ),
                border = null,
                modifier = Modifier.height(32.dp)
            )
        }
    }
}

@Composable
fun BooksSlider(
    books: List<BookShort>,
    modifier: Modifier = Modifier,
    placeholderModifier: Modifier = Modifier,
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 8.dp),
        reverseLayout = true,
        modifier = modifier
            .fillMaxWidth()
            .height(240.dp)
    ) {
        items(books) { BookItem(book = it, placeholderModifier = placeholderModifier) }
    }
}

@Composable
fun BookItem(
    book: BookShort,
    modifier: Modifier = Modifier,
    placeholderModifier: Modifier = Modifier,
) {
    val width = LocalDensity.current.run { 150.dp.roundToPx() }
    Column(modifier = modifier
        .width(150.dp)
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
                model = book.coverUrl(book.coverImage, width),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(150.dp)
                    .clip(MaterialTheme.shapes.medium),
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
                books = promotion.items,
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
fun PreviewCategoriesSlider() {
    CategoriesSlider(categories = listOf())
}

@Preview(showBackground = true)
@Composable
fun PreviewBooksSlider() {
    BooksSlider(books = previewBooks)
}

@Preview(showBackground = true)
@Composable
fun PreviewHomeUi() {
    HomeUi(
        state = HomeState.Success(
            categories = listOf(),
            promotions = previewPromotions
        )
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
