package khanesh.shared.feature.home.mapper

import khanesh.shared.core.model.BookShort
import khanesh.shared.core.model.Promotion
import khanesh.shared.storage.Promotions

fun Promotions.toBookShort() = BookShort(
    id = bookId,
    title = title,
    compiler = author,
    voiceActor = null,
    coverImage = coverImage,
    comingSoon = false,
    comingSoonPromotion = null,
    averageRate = averageRate?.toDouble(),
    rateCount = rateCount,
    hasDonation = false,
    slug = "",
)

fun BookShort.toPromotions(promotionTitle: String) = Promotions(
    id = 0,
    promotionTitle = promotionTitle,
    bookId = id,
    title = title,
    author = compiler,
    coverImage = coverImage,
    averageRate = averageRate?.toFloat(),
    rateCount = rateCount,
)

fun List<Promotions>.toListPromotion() = groupBy { it.promotionTitle }
    .map { entry ->
        Promotion(
            title = entry.key,
            items = entry.value.map { it.toBookShort() }
        )
    }

fun List<Promotion>.toListPromotions() = flatMap { promotion ->
    promotion.items.map { it.toPromotions(promotion.title) }
}
