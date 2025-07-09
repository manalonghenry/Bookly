package com.example.bookly.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.bookly.BookDoc

@Composable
fun BookCard(book: BookDoc, modifier: Modifier = Modifier) {
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
            .padding(4.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp).padding(16.dp)) {
            if (coverUrl != null) {
                AsyncImage(
                    model = coverUrl,
                    contentDescription = "${book.title} cover",
                    modifier = Modifier
                        .size(400.dp)
                        .clip(RoundedCornerShape(8.dp))
                )
            }

            Spacer(modifier = Modifier.height( 10.dp))

            // Text info
            Column(modifier = Modifier.weight(1f)) {
                Text(text = book.title ?: "Untitled", style = MaterialTheme.typography.titleMedium, fontSize = 30.sp)
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = authors, style = MaterialTheme.typography.bodySmall, fontSize = 25.sp)
                Spacer(modifier = Modifier.height(10.dp))
                rating?.let {
                    Text(text = "⭐ ${String.format("%.1f", it)}", style = MaterialTheme.typography.bodySmall, fontSize = 20.sp)
                }
                Spacer(modifier = Modifier.height(10.dp))
                Text(text = description, style = MaterialTheme.typography.bodySmall, fontSize = 17.sp)

            }
        }
    }
}