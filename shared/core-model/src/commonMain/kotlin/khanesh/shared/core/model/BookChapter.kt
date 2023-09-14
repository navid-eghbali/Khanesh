package khanesh.shared.core.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BookChapter(
    @SerialName("uuid") val uuid: String,
    @SerialName("id") val id: Long,
    @SerialName("book_id") val bookId: Long,
    @SerialName("caption") val caption: String,
    @SerialName("size") val size: Long,
    @SerialName("index") val index: Int,
    @SerialName("duration") val duration: Long,
) {

    val downloadUrl: String = "https://api.vavkhan.com/book/$bookId/encrypted_chapter/$id"
}
