package com.example.bookly.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun BottomNavigationBar(selected: String, onSelect: (String) -> Unit) {
    NavigationBar(containerColor = MaterialTheme.colorScheme.primary)
     {
        NavigationBarItem(
            selected = selected == "profile",
            onClick = { onSelect("profile") },
            icon = { Icon(Icons.Default.Person, contentDescription = "Profile") },
            label = { Text("Profile") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                // the pill/highlight behind the selected item:
                indicatorColor = Color.White,
            )
        )
         NavigationBarItem(
             selected = selected == "discover",
             onClick = { onSelect("discover") },
             icon = { Icon(Icons.Default.Search, contentDescription = "Discover") },
             label = { Text("Discover") },
             alwaysShowLabel = true,
             colors = NavigationBarItemDefaults.colors(
                 // the pill/highlight behind the selected item:
                 indicatorColor = Color.White,
             )
         )
        NavigationBarItem(
            selected = selected == "myLists",
            onClick = { onSelect("myLists") },
            icon = { Icon(Icons.AutoMirrored.Filled.List, contentDescription = "My Lists") },
            label = { Text("My Lists") },
            alwaysShowLabel = true,
            colors = NavigationBarItemDefaults.colors(
                // the pill/highlight behind the selected item:
                indicatorColor = Color.White,
            )
        )
    }
}