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
            Text("No books available")
        }
    } else {
        val book = books.first()

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Box(modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(15.dp)
                .fillMaxWidth()
                .height(725.dp)
            ){
                BookCard(
                    book = book,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            // Reaction bar
            Box(
                modifier = Modifier
                    .align(Alignment.BottomCenter)
                    .fillMaxWidth()
                    .height(170.dp)
                    .background(Color.Transparent)
                    .padding(4.dp)
            ) {
                BookReactionBar(
                    currentBook = book,
                    onReact     = { reaction ->
                        when (reaction) {
                            BookReaction.READ_LIKED      -> readLiked.add(book)
                            BookReaction.READ_DISLIKED   -> readDisliked.add(book)
                            BookReaction.INTERESTED      -> interested.add(book)
                            BookReaction.NOT_INTERESTED  -> notInterested.add(book)
                        }
                        // move to next book
                        books.remove(book)
                    }
                )
            }
        }


    }
}
