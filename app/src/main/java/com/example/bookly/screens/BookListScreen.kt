package com.example.bookly.screens

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookly.components.*
import com.example.bookly.BookDoc

@Composable
fun BookListScreen(
    books: List<BookDoc>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        items(books) { BookCard(it) }
    }
}
