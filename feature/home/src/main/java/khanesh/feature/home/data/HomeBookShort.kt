package khanesh.feature.home.data

import androidx.compose.runtime.Immutable
import khanesh.shared.core.model.BookShort
import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toPersistentList

@Immutable
internal data class HomeBookShort(
    val id: Long,
    val title: String,
    val compiler: String,
    val voiceActor: String?,
    val coverImage: String,
    val comingSoon: Boolean,
    val comingSoonPromotion: Long?,
    val averageRate: Double?,
    val rateCount: Int,
    val hasDonation: Boolean,
    val slug: String,
) {

    fun coverUrl(uuid: String, width: Int, height: Int, type: String = "cover") =
        "https://www.vavkhan.com/image/$uuid/$width/$height/$type"
}

internal fun List<BookShort>.toHomeBookShorts(): ImmutableList<HomeBookShort> = map {
    HomeBookShort(
        it.id,
        it.title,
        it.compiler,
        it.voiceActor,
        it.coverImage,
        it.comingSoon,
        it.comingSoonPromotion,
        it.averageRate,
        it.rateCount,
        it.hasDonation,
        it.slug,
    )
}.toPersistentList()
