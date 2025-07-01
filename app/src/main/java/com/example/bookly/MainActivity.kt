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
import com.example.bookly.ui.theme.BooklyTheme
import android.util.Log
import androidx.compose.foundation.background
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.example.bookly.components.*
import com.example.bookly.screens.*



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks("fantasy", 10) // Book limit
                if (response.isSuccessful) {
                    val books = response.body()?.docs ?: emptyList<BookDoc>()
                    for (book in books) {
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
    val books = remember { mutableStateListOf<BookDoc>() } // What makes books persist
    LaunchedEffect(Unit) {
        if (books.isEmpty()) {
            val response = RetrofitInstance
                .api
                .searchBooksBySubject(subjects = listOf("fantasy"), limit = 1) // only 1 book

            if (response.isSuccessful) {
                response.body()?.docs
                    ?.let    { books.addAll(it) }
            }
        }
    }


    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {
            BottomNavigationBar(
                selected = selectedScreen,
                onSelect = { selectedScreen = it } // Updates selectedScreen with newly selected item
            )
        }
    ) { innerPadding ->
        when (selectedScreen) {
            "profile" -> ProfileScreen()
            "discover" -> DiscoverScreen(
                books    = books,
                modifier = Modifier.padding(innerPadding).fillMaxSize()
            )
            "myLists" -> MyListsScreen(Modifier.padding(innerPadding))
        }
    }
}