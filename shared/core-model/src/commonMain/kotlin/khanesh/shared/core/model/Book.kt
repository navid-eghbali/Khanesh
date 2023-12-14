package khanesh.shared.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Book(
    @SerialName("id") val id: Long,
    @SerialName("title") val title: String,
    @SerialName("compiler") val compiler: String,
    @SerialName("voice_actor") val voiceActor: String,
    @SerialName("cover_image") val coverImage: String,
    @SerialName("coming_soon") val comingSoon: Boolean,
    @SerialName("coming_soon_promotion") val comingSoonPromotion: Long?,
    @SerialName("average_rate") val averageRate: Double?,
    @SerialName("rate_count") val rateCount: Int,
    @SerialName("has_donation") val hasDonation: Boolean,
    @SerialName("slug") val slug: String,
    @SerialName("price") val price: Long?,
    @SerialName("undiscounted_price") val undiscountedPrice: Long?,
    @SerialName("completed_at") val completedAt: Long,
    @SerialName("owned") val owned: Boolean,
) {

    val sampleUrl: String = "https://api.vavkhan.com/book/$id/sample/"
}
