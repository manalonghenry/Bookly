package com.example.bookly

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.bookly.ui.theme.BooklyTheme
import android.util.Log
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import com.example.bookly.BookDoc
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks("fantasy")
                if (response.isSuccessful) {
                    val books = response.body()?.docs ?: emptyList<BookDoc>()
                    for (book in books.take(10)) { // How many books
                        Log.d("BookAPI", "Title: ${book.title}, Author(s): ${book.author_name?.joinToString()}")
                    }
                } else {
                    Log.e("BookAPI", "Error: ${response.code()}")
                }
            } catch (e: Exception) {
                Log.e("BookAPI", "Exception: ${e.message}")
            }
        }

        setContent {
            BooklyTheme {
                BookScreen()
            }
        }

    }
}

@Composable
fun BookScreen() {
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
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun BookList(books: List<BookDoc>, modifier: Modifier = Modifier) {
    LazyColumn(modifier = modifier) {
        items(books) { book ->
            BookCard(book)
        }
    }
}

@Composable
fun BookCard(book: BookDoc) {
    val authors = book.author_name?.joinToString(", ") ?: "Unknown Author"
    var description by remember { mutableStateOf("Loading description...") }
    var rating by remember {mutableStateOf<Float?>(null)}
    LaunchedEffect(book.key) {
        book.key?.let { key ->
            val workId = key.removePrefix("/works/")
            try {
                // Fetch description
                val descResponse = RetrofitInstance.api.getWorkDetails(workId)
                val desc = descResponse.body()?.description
                description = when (desc) {
                    is String -> desc
                    is Map<*, *> -> desc["value"] as? String ?: "No description available."
                    else -> "No description available."
                }

                // Fetch rating
                val ratingResponse = RetrofitInstance.api.getWorkRating(workId)
                if (ratingResponse.isSuccessful) {
                    rating = ratingResponse.body()?.summary?.average
                }

            } catch (e: Exception) {
                description = "Error loading description."
            }
        }
    }

    Column(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth()
    ) {
        Text(text = book.title ?: "Untitled", style = MaterialTheme.typography.titleMedium)
        Text(text = authors, style = MaterialTheme.typography.bodyMedium)
        Text(text = description, style = MaterialTheme.typography.bodySmall)
        rating?.let {
            Text(
                text = "⭐ Rating: ${String.format("%.1f", it)}",
                style = MaterialTheme.typography.bodySmall
            )
        }
    }
}



@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BooklyTheme {
        Greeting("Android")
    }
}