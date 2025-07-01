package com.example.bookly.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookly.components.*
import com.example.bookly.BookDoc

@Composable
fun DiscoverScreen(
    books: List<BookDoc>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        when (val book = books.firstOrNull()) {
            null -> Text("No books available", style = MaterialTheme.typography.bodyLarge)
            else -> BookCard(
                book = book,
                modifier = Modifier.fillMaxSize() // card fills the Box
            )
        }
    }
}
