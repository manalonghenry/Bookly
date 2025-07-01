package com.example.bookly.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun FilterChecklist(
    options: List<String>,
    modifier: Modifier = Modifier,
    onSelectionChanged: (Map<String, Boolean>) -> Unit = {}
) {
    val selected = remember { mutableStateMapOf<String, Boolean>().apply {
        options.forEach { put(it, false) }
    } }

    Column(modifier = modifier) {
        options.forEach { option ->
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Checkbox(
                    checked = selected[option] == true,
                    onCheckedChange = { checked ->
                        selected[option] = checked
                        onSelectionChanged(selected.toMap())
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }
    }
}