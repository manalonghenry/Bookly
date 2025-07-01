package com.example.bookly.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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
    modifier: Modifier = Modifier
) {
    val genres = listOf(
        "Fantasy",
        "Horror",
        "Science Fiction",
        "Mystery",
        "Romance",
        "Romantasy"
    )
    val advancedFiltering = listOf(
        "Published in 20th Centry",
        "Published in 21st Century"
    )

    val fantasyElements = listOf(
        "Dragons",
        "Fairies",
        "Elves",
        "Vampires",
        "Werewolves"
    )

    val scifiElements = listOf(
        "Aliens",
        "Time Travel"
    )

    // track the user’s selection
    var selected by remember {
        mutableStateOf<Map<String, Boolean>>(
            genres.associateWith { false }
        )
    }

    val scrollState = rememberScrollState()

        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(scrollState)
        ) {
            ProfileCard(name = "Jane Doe", modifier = Modifier.fillMaxWidth())

            Spacer(Modifier.height(24.dp))
            Text(
                "Filter by genre:",
                modifier = Modifier.align(Alignment.Start),
                fontSize =  20.sp,
                color = PinkPrimaryDark
            )

            FilterChecklist(
                options              = genres,
                modifier             = Modifier.fillMaxWidth(),
                onSelectionChanged   = { sel -> selected = sel }
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Advanced filtering options:",
                fontSize = 20.sp,
                color = PinkPrimaryDark
            )
            Spacer(Modifier.height(5.dp))
            Text(
                text = "Dates:",
                fontSize = 17.sp,
                color = Color(0xFF517891)
            )
            FilterChecklist(
                options              = advancedFiltering,
                modifier             = Modifier.fillMaxWidth(),
                onSelectionChanged   = { sel -> selected = sel }
            )
            Text(
                text = "Specific fantasy elements:",
                fontSize = 17.sp,
                color = Color(0xFF517891)
            )
            FilterChecklist(
                options              = fantasyElements,
                modifier             = Modifier.fillMaxWidth(),
                onSelectionChanged   = { sel -> selected = sel }
            )
            Text(
                text = "Specific science fiction elements:",
                fontSize = 17.sp,
                color = Color(0xFF517891)
            )
            FilterChecklist(
                options              = fantasyElements,
                modifier             = Modifier.fillMaxWidth(),
                onSelectionChanged   = { sel -> selected = sel }
            )

    }
}
