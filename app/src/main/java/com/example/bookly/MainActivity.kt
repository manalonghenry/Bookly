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


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        lifecycleScope.launch {
            try {
                val response = RetrofitInstance.api.searchBooks("fantasy")
                if (response.isSuccessful) {
                    val books = response.body()?.docs ?: emptyList()
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
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    BooklyTheme {
        Greeting("Android")
    }
}