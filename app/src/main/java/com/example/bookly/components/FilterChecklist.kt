package com.example.bookly.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

/**
 * A stateless checklist of filter options.
 * The parent drives selection via [selected] and is notified on changes.
 *
 * @param options List of string options to display as checkboxes.
 * @param selected Map of option → checked state.
 * @param onSelectionChanged Callback invoked with the updated map when any box is toggled.
 * @param modifier Modifier to apply to the root Column.
 */
@Composable
fun FilterChecklist(
    options: List<String>,
    selected: Map<String, Boolean>,
    onSelectionChanged: (Map<String, Boolean>) -> Unit,
    modifier: Modifier = Modifier
) {
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
                        // build a new map so we don’t mutate the caller’s
                        val updated = selected.toMutableMap().apply {
                            this[option] = checked
                        }
                        onSelectionChanged(updated)
                    }
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = option)
            }
        }
    }
}
