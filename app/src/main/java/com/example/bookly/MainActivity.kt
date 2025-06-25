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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.ui.draw.clip
import coil.compose.AsyncImage
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search


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
                HomeScreenWithBottomNav();
            }
        }

    }
}

@Composable
fun WelcomeScreen(onContinue: () -> Unit){
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "📚 Welcome to Bookly!",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Swipe through books and find your next read.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onContinue) {
                Text("Get Started")
            }
        }
    }
}

@Composable
fun HomeScreenWithBottomNav() {
    var selectedScreen by remember { mutableStateOf("discover") }

    Scaffold(
        bottomBar = {
            BottomNavigationBar(
                selected = selectedScreen,
                onSelect = { selectedScreen = it }
            )
        }
    ) { innerPadding ->
        when (selectedScreen) {
            "discover" -> BookListScreen(Modifier.padding(innerPadding))
            "profile" -> ProfileScreen(Modifier.padding(innerPadding))
        }
    }
}


@Composable
fun BottomNavigationBar(selected: String, onSelect: (String) -> Unit) {
    NavigationBar {
        NavigationBarItem(
            selected = selected == "discover",
            onClick = { onSelect("discover") },
            icon = { Icon(Icons.Default.Search, contentDescription = "Discover") },
            label = { Text("Discover") }
        )
        NavigationBarItem(
            selected = selected == "profile",
            onClick = { onSelect("profile") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") }
        )
    }
}

@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Text("👤 Your Profile", style = MaterialTheme.typography.headlineMedium)
        Text("This is your profile page.")
    }
}

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
    var rating by remember { mutableStateOf<Float?>(null) }

    val coverUrl = book.cover_i?.let {
        "https://covers.openlibrary.org/b/id/$it-M.jpg"
    }

    LaunchedEffect(book.key) {
        book.key?.let { key ->
            val workId = key.removePrefix("/works/")
            try {
                val descResponse = RetrofitInstance.api.getWorkDetails(workId)
                val desc = descResponse.body()?.description
                description = when (desc) {
                    is String -> desc
                    is Map<*, *> -> desc["value"] as? String ?: "No description available."
                    else -> "No description available."
                }

                val ratingResponse = RetrofitInstance.api.getWorkRating(workId)
                if (ratingResponse.isSuccessful) {
                    rating = ratingResponse.body()?.summary?.average
                }
            } catch (e: Exception) {
                description = "Error loading description."
            }
        }
    }

    Card(
        modifier = Modifier
            .padding(12.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(modifier = Modifier.padding(16.dp)) {
            if (coverUrl != null) {
                AsyncImage(
                    model = coverUrl,
                    contentDescription = "${book.title} cover",
                    modifier = Modifier
                        .size(100.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            // Text info on the right
            Column(modifier = Modifier.weight(1f)) {
                Text(text = book.title ?: "Untitled", style = MaterialTheme.typography.titleMedium)
                Text(text = authors, style = MaterialTheme.typography.bodySmall)
                Text(text = description, style = MaterialTheme.typography.bodySmall, maxLines = 4)
                rating?.let {
                    Text(text = "⭐ ${String.format("%.1f", it)}", style = MaterialTheme.typography.bodySmall)
                }
            }
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