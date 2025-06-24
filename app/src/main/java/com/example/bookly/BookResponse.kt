package com.example.bookly


data class BookResponse(
    val docs: List<BookDoc>
)

data class BookDoc(
    val title: String?,
    val author_name: List<String>?,
    val cover_i: Int?,
    val key: String?
)
