package com.example.bookly.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.LibraryBooks
import androidx.compose.material.icons.filled.ThumbUpOffAlt
import androidx.compose.material.icons.outlined.Block
import androidx.compose.material.icons.outlined.ThumbDown
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.ui.graphics.Color


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopNavigationBar(tabs:List<String>, selected: String, onSelect: (String) -> Unit) {
    TopAppBar(
        title = { Text("\uD83D\uDCDA My Lists") },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = Color.DarkGray,
            actionIconContentColor = MaterialTheme.colorScheme.onPrimary
        ),
        actions = {
            IconButton(onClick = { onSelect("Interested") }) {
                Icon(
                    imageVector = Icons.AutoMirrored.Outlined.LibraryBooks,
                    contentDescription = "Interested",
                    tint = if (selected == "Interested")
                        MaterialTheme.colorScheme.onPrimary
                    else
                       Color.DarkGray
                )
            }
            IconButton(onClick = { onSelect("Liked") }) {
                Icon(
                    imageVector = Icons.Default.ThumbUpOffAlt,
                    contentDescription = "Liked",
                    tint = if (selected == "Liked")
                        MaterialTheme.colorScheme.onPrimary
                    else
                        Color.DarkGray                )
            }
            IconButton(onClick = { onSelect("Disliked") }) {
                Icon(
                    imageVector = Icons.Outlined.ThumbDown,
                    contentDescription = "Disliked",
                    tint = if (selected == "Disliked")
                        MaterialTheme.colorScheme.onPrimary
                    else
                        Color.DarkGray                )
            }
            IconButton(onClick = { onSelect("Not Interested") }) {
                Icon(
                    imageVector = Icons.Outlined.Block,
                    contentDescription = "Not Interested",
                    tint = if (selected == "Not Interested")
                        MaterialTheme.colorScheme.onPrimary
                    else
                        Color.DarkGray                )
            }
        }
    )
}