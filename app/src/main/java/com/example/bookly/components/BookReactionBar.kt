package com.example.bookly.components

import android.R.attr.background
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookly.BookDoc

@Composable
fun BookReactionBar(
    currentBook: BookDoc,
    onReact: (BookReaction) -> Unit
) {
    Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.spacedBy(5.dp)
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
        contentPadding = PaddingValues(
            horizontal = 12.dp,
            vertical = 2.dp
        )
    ) {
        Text(text = text, color = Color.Black, fontSize = 12.sp)
    }
}
