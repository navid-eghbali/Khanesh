package khanesh.shared.core.model

import kotlinx.serialization.Serializable

@Serializable
data class Rating(
    val book: Double?,
    val voice: Double?,
)
