package com.example.bookly.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.bookly.components.FilterChecklist
import com.example.bookly.components.ProfileCard
import com.example.bookly.ui.theme.PinkPrimaryDark

@Composable
fun ProfileScreen(
    selectedGenres: Map<String, Boolean>,
    onGenresChanged: (Map<String, Boolean>) -> Unit,
    selectedDates: Map<String, Boolean>,
    onDatesChanged: (Map<String, Boolean>) -> Unit,
    selectedNonfiction: Map<String, Boolean>,
    onNonfictionChanged: (Map<String, Boolean>) -> Unit,
    selectedFantasy: Map<String, Boolean>,
    onFantasyChanged: (Map<String, Boolean>) -> Unit,
    selectedHorror: Map<String, Boolean>,
    onHorrorChanged: (Map<String, Boolean>) -> Unit,
    selectedScifi: Map<String, Boolean>,
    onScifiChanged: (Map<String, Boolean>) -> Unit,
    selectedRomance: Map<String, Boolean>,
    onRomanceChanged: (Map<String, Boolean>) -> Unit,
    modifier: Modifier = Modifier
) {
    val genres            = selectedGenres.keys.toList()
    val dates = selectedDates.keys.toList()
    val nonfictionTopics  = selectedNonfiction.keys.toList()
    val fantasyElements   = selectedFantasy.keys.toList()
    val horrorElements    = selectedHorror.keys.toList()
    val scifiElements     = selectedScifi.keys.toList()
    val romanceElements   = selectedRomance.keys.toList()

    val scrollState = rememberScrollState()

    Column(
        modifier = modifier
            .verticalScroll(scrollState)
            .padding(16.dp)
    ) {
        ProfileCard(name = "Jane Doe", modifier = Modifier.fillMaxWidth())

        Spacer(Modifier.height(24.dp))
        Text(
            text     = "Filter by genre:",
            fontSize = 20.sp,
            color    = PinkPrimaryDark,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text     = "Note: by selecting these genres, you will see books from all sub-categories of this genre. " +
                    "Therefore, if you want to see ONLY specific sub-category books of a specific genre, then select it " +
                    "in the Advanced Filtering Options section and do NOT select the overall genre here.",
            fontSize = 14.sp,
            color = Color(0xFF47506B),
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(8.dp))
        FilterChecklist(
            options            = genres,
            selected           = selectedGenres,
            onSelectionChanged = onGenresChanged,
            modifier           = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text     = "Advanced filtering options:",
            fontSize = 20.sp,
            color    = PinkPrimaryDark,
            modifier = Modifier.align(Alignment.Start)
        )
        Spacer(Modifier.height(8.dp))
        Text(
            text     = "Dates:",
            fontSize = 17.sp,
            color = Color(0xFF4292C6),
            modifier = Modifier.align(Alignment.Start)
        )
        FilterChecklist(
            options            = dates,
            selected           = selectedDates,
            onSelectionChanged = onDatesChanged,
            modifier           = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text     = "Specific non-fiction topics:",
            fontSize = 17.sp,
            color = Color(0xFF4292C6),
            modifier = Modifier.align(Alignment.Start)
        )
        FilterChecklist(
            options            = nonfictionTopics,
            selected           = selectedNonfiction,
            onSelectionChanged = onNonfictionChanged,
            modifier           = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text     = "Specific fantasy elements:",
            fontSize = 17.sp,
            color = Color(0xFF4292C6),
            modifier = Modifier.align(Alignment.Start)
        )
        FilterChecklist(
            options            = fantasyElements,
            selected           = selectedFantasy,
            onSelectionChanged = onFantasyChanged,
            modifier           = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text     = "Specific horror elements:",
            fontSize = 17.sp,
            color = Color(0xFF4292C6),
            modifier = Modifier.align(Alignment.Start)
        )
        FilterChecklist(
            options            = horrorElements,
            selected           = selectedHorror,
            onSelectionChanged = onHorrorChanged,
            modifier           = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text     = "Specific science fiction elements:",
            fontSize = 17.sp,
            color = Color(0xFF4292C6),
            modifier = Modifier.align(Alignment.Start)
        )
        FilterChecklist(
            options            = scifiElements,
            selected           = selectedScifi,
            onSelectionChanged = onScifiChanged,
            modifier           = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(16.dp))
        Text(
            text     = "Specific romance elements:",
            fontSize = 17.sp,
            color = Color(0xFF4292C6),
            modifier = Modifier.align(Alignment.Start)
        )
        FilterChecklist(
            options            = romanceElements,
            selected           = selectedRomance,
            onSelectionChanged = onRomanceChanged,
            modifier           = Modifier.fillMaxWidth()
        )
    }
}
