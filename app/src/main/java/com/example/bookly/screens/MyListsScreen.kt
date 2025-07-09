package com.example.bookly.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.example.bookly.components.*
import com.example.bookly.BookDoc

@Composable
fun MyListsScreen(
    likedBooks: List<BookDoc>,
    dislikedBooks: List<BookDoc>,
    interestedBooks: List<BookDoc>,
    notInterestedBooks: List<BookDoc>,
    modifier: Modifier = Modifier
) {
    val tabs = listOf("Liked", "Disliked", "Interested", "Not Interested")
    var selectedTab by remember { mutableStateOf(tabs[0]) }

    Scaffold(
        topBar = {
            TopNavigationBar(
                tabs      = tabs,
                selected  = selectedTab,
                onSelect  = { selectedTab = it }
            )
        },
        modifier = modifier
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            when (selectedTab) {
                "Liked" -> BookListContent(likedBooks)
                "Disliked" -> BookListContent(dislikedBooks)
                "Interested" -> BookListContent(interestedBooks)
                "Not Interested" -> BookListContent(notInterestedBooks)
            }
        }
    }
}

@Composable
private fun BookListContent(books: List<BookDoc>) {
    LazyColumn(
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(books) { book ->
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
            ) {
                BookCard(
                    book     = book,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}