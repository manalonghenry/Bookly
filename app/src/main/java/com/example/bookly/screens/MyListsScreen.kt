package com.example.bookly.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.example.bookly.components.*

@Composable
fun MyListsScreen(modifier: Modifier = Modifier){
    var selectedTab by remember { mutableStateOf("Liked Books") }

    Scaffold(
        topBar = {
            TopNavigationBar(
                selected = selectedTab,
                onSelect = { selectedTab = it }
            )
        }
    ) { innerPadding ->
        Column(Modifier.padding(innerPadding)) {
            Text("$selectedTab")
            // Show different content based on tab
        }
    }
}