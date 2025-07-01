package com.example.bookly.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookly.components.FilterChecklist
import com.example.bookly.components.ProfileCard

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    val genres = listOf(
        "Fantasy",
        "Horror",
        "Science Fiction",
        "Mystery",
        "Romance"
    )

    // track the user’s selection
    var selected by remember {
        mutableStateOf<Map<String, Boolean>>(
            genres.associateWith { false }
        )
    }

    Scaffold { innerPadding ->
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            ProfileCard(name = "Jane Doe", modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(24.dp))
            Text(
                "Filter by genre:",
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier.align(Alignment.Start)
            )

            FilterChecklist(
                options              = genres,
                modifier             = Modifier.fillMaxWidth(),
                onSelectionChanged   = { sel -> selected = sel }
            )
        }
    }
}
