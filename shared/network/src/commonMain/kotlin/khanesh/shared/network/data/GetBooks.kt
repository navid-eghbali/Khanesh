package khanesh.shared.network.data

import khanesh.shared.core.model.Book
import kotlinx.serialization.Serializable

object GetBooks {

    @Serializable
    data class Response(
        val count: Int,
        val next: String?,
        val previous: String?,
        val results: List<Book>
    )
}
