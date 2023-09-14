package khanesh.shared.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Promotion(
    @SerialName("title") val title: String,
    @SerialName("items") val items: List<BookShort>,
)
