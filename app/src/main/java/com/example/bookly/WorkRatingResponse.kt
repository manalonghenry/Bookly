package com.example.bookly

data class WorkRatingResponse(
    val summary: RatingSummary?
)

data class RatingSummary(
    val average: Float?,
    val count: Int?
)
