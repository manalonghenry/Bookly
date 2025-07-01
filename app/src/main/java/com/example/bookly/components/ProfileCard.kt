package com.example.bookly.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun ProfileCard(name: String, modifier: Modifier = Modifier){
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(120.dp)
            .padding(16.dp),
        shape    = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),       // inside padding
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector   = Icons.Default.Person,
                contentDescription = "Profile",
                modifier      = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.width(16.dp)) // Horizontal space
            Text(
                text  = name,
                style = MaterialTheme.typography.headlineSmall
            )
        }
    }
}