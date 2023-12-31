package khanesh.feature.home.data

import androidx.compose.runtime.Immutable
import khanesh.shared.core.model.Promotion
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Immutable
internal data class HomePromotion(
    val title: String,
    val items: ImmutableList<HomeBookShort>,
)

internal fun List<Promotion>.toHomePromotions(): ImmutableList<HomePromotion> = map {
    HomePromotion(
        title = it.title,
        items = it.items.toHomeBookShorts(),
    )
}.toPersistentList()
