package khanesh.shared.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookShort(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("compiler") val compiler: String,
    @SerialName("voice_actor") val voiceActor: String?,
    @SerialName("cover_image") val coverImage: String,
    @SerialName("coming_soon") val comingSoon: Boolean,
    @SerialName("coming_soon_promotion") val comingSoonPromotion: Long?,
    @SerialName("average_rate") val averageRate: Double?,
    @SerialName("rate_count") val rateCount: Int,
    @SerialName("has_donation") val hasDonation: Boolean,
    @SerialName("slug") val slug: String,
) {

    fun coverUrl(uuid: String, width: Int, height: Int, type: String = "cover") =
        "https://www.vavkhan.com/image/$uuid/$width/$height/$type"
}
