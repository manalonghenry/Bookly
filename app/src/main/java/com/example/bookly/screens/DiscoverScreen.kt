package com.example.bookly.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
    modifier: Modifier = Modifier
) {
    // Keep track of reactions
    val readLiked = remember { mutableStateListOf<BookDoc>() }
    val readDisliked = remember { mutableStateListOf<BookDoc>() }
    val interested = remember { mutableStateListOf<BookDoc>() }
    val notInterested = remember { mutableStateListOf<BookDoc>() }

    if (books.isEmpty()) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Text("No books available", style = MaterialTheme.typography.bodyLarge)
        }
    } else {
        val book = books.first()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .background(Color.White), // Ensure background contrast
            verticalArrangement = Arrangement.Top
        ) {
            // Book Card
            BookCard(
                book = book,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Reaction Buttons (with debug styling)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color(0xFFE0E0E0)) // Light grey background
                    .padding(8.dp)
            ) {
                BookReactionBar(
                    currentBook = book,
                    onReact = { reaction ->
                        when (reaction) {
                            BookReaction.READ_LIKED -> readLiked.add(book)
                            BookReaction.READ_DISLIKED -> readDisliked.add(book)
                            BookReaction.INTERESTED -> interested.add(book)
                            BookReaction.NOT_INTERESTED -> notInterested.add(book)
                        }

                        books.remove(book)
                        println("User selected: $reaction")
                    }
                )
            }
        }
    }
}
