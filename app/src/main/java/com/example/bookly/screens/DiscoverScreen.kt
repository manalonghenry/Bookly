package com.example.bookly.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bookly.BookDoc
import com.example.bookly.components.BookReaction
import com.example.bookly.components.BookCard
import com.example.bookly.components.BookReactionBar

@Composable
fun DiscoverScreen(
    books: MutableList<BookDoc>,
    onReact: (BookDoc, BookReaction) -> Unit,
    modifier: Modifier = Modifier
) {
    if (books.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No books available")
        }
        return
    }

    val book = books.first()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Box(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(16.dp)
                .fillMaxWidth()
        ) {
            BookCard(book = book, modifier = Modifier.fillMaxWidth())
        }

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .background(Color(0x00000000))
                .padding(8.dp)
        ) {
            BookReactionBar(
                currentBook = book,
                onReact     = { reaction ->
                    onReact(book, reaction)
                },
            )
        }
    }
}
