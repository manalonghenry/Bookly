package com.example.bookly.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.bookly.BookDoc

@Composable
fun BookReactionBar(
    currentBook: BookDoc,
    onReact: (BookReaction) -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.Gray)
            .padding(8.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        ReactionButton("👍 Liked") { onReact(BookReaction.READ_LIKED) }
        ReactionButton("👎 Disliked") { onReact(BookReaction.READ_DISLIKED) }
        ReactionButton("⭐ Interested") { onReact(BookReaction.INTERESTED) }
        ReactionButton("❌ Not Interested") { onReact(BookReaction.NOT_INTERESTED) }
    }
}

@Composable
fun ReactionButton(text: String, onClick: () -> Unit) {
    Button(
        onClick = onClick,
        modifier = Modifier
            .padding(horizontal = 4.dp)
    ) {
        Text(text = text, color = Color.Black)
    }
}
