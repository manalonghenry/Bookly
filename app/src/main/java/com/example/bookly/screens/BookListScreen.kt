package com.example.bookly.screens

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import com.example.bookly.components.*
import com.example.bookly.BookDoc

@Composable
fun BookListScreen(modifier: Modifier = Modifier) {
    val books = remember { mutableStateListOf<BookDoc>() }

    LaunchedEffect(Unit) {
        try {
            val response = RetrofitInstance.api.searchBooks("fantasy")
            if (response.isSuccessful) {
                val results = response.body()?.docs ?: emptyList<BookDoc>()
                books.addAll(results)
            }
        } catch (e: Exception) {
            Log.e("BookAPI", "Exception: ${e.message}")
        }
    }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        BookList(
            books = books,
            modifier = Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BookList(books: List<BookDoc>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(books) { book ->
            BookCard(book)
        }
    }
}