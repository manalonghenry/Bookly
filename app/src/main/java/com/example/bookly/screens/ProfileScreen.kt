package com.example.bookly.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.bookly.components.ProfileCard


@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    var name: String = "Jane Doe";

    Scaffold { innerPadding ->
        Column(
            modifier.fillMaxSize().padding(innerPadding).padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            ProfileCard(name = name, modifier = Modifier.fillMaxWidth())
        }
    }
}