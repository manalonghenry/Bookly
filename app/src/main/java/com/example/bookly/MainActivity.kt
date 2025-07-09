package com.example.bookly

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.bookly.ui.theme.BooklyTheme
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import com.example.bookly.components.*
import com.example.bookly.screens.*



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BooklyTheme {
                HomeScreenWithBottomNav();
            }
        }

    }
}

@Composable
fun WelcomeScreen(onContinue: () -> Unit){
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .padding(24.dp),
            verticalArrangement = Arrangement.Center
        ) {
            Text(
                text = "📚 Welcome to Bookly!",
                style = MaterialTheme.typography.headlineMedium
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = "Swipe through books and find your next read.",
                style = MaterialTheme.typography.bodyLarge
            )

            Spacer(modifier = Modifier.height(24.dp))

            Button(onClick = onContinue) {
                Text("Get Started")
            }
        }
    }
}

@Composable
fun HomeScreenWithBottomNav() {
    var selectedScreen by remember { mutableStateOf("discover") }

    // filter‐option lists
    val genres            = listOf("Nonfiction","Fantasy","Horror","Science Fiction","Mystery","Romance")
    val advancedFiltering = listOf("Published in 20th Century","Published in 21st Century to 2015",
        "Published 2016 to now")
    val nonfictionTopics  = listOf("True Crime","Biographies","Science","Self Help","Politics","History")
    val fantasyElements   = listOf("Romance", "Dragons","Fairies","Elves","Vampires","Werewolves")
    val horrorElements    = listOf("Paranormal","Supernatural", "Zombies")
    val scifiElements     = listOf("Aliens","Time Travel","Artificial Intelligence", "Zombies", "Dystopia", "Space Opera")
    val romanceElements   = listOf("Sports","Dark Romance","Contemporary")

    // Hoist each selection map into this parent composable -- so it persists
    var genreSelection      by remember { mutableStateOf(genres.associateWith { false }) }
    var advancedSelection   by remember { mutableStateOf(advancedFiltering.associateWith { false }) }
    var nonfictionSelection by remember { mutableStateOf(nonfictionTopics.associateWith { false }) }
    var fantasySelection    by remember { mutableStateOf(fantasyElements.associateWith { false }) }
    var horrorSelection     by remember { mutableStateOf(horrorElements.associateWith { false }) }
    var scifiSelection      by remember { mutableStateOf(scifiElements.associateWith { false }) }
    var romanceSelection    by remember { mutableStateOf(romanceElements.associateWith { false }) }

    val seenIds = remember { mutableStateListOf<String>() }

    // books‐loading logic
    val books = remember { mutableStateListOf<BookDoc>() }
    val likedBooks = remember { mutableStateListOf<BookDoc>() }
    val dislikedBooks = remember { mutableStateListOf<BookDoc>() }
    val interestedBooks = remember { mutableStateListOf<BookDoc>() }
    val notInterestedBooks = remember { mutableStateListOf<BookDoc>() }

    LaunchedEffect(
        genreSelection,
        advancedSelection,
        nonfictionSelection,
        fantasySelection,
        horrorSelection,
        scifiSelection,
        romanceSelection
    ) {
        // build query string
        val raw       = QueryBuilder.buildQuery(
            genreSelection, advancedSelection,
            nonfictionSelection, fantasySelection,
            horrorSelection, scifiSelection,
            romanceSelection
        )
        val finalQ    = raw.ifBlank { "subject:fantasy" }
        val resp      = RetrofitInstance.api.advancedSearch(
            query = finalQ,
            limit = 20
        )

        if (resp.isSuccessful) {
            // clear old results
            books.clear()

            // take only unseen ones
            val incoming  = resp.body()?.docs.orEmpty()
            val newBooks  = incoming.filter { it.key != null && it.key !in seenIds }

            // show them
            books.addAll(newBooks)

            // don’t update seenIds here
        } else {
            Log.e("HomeScreen", "API error ${resp.code()}")
        }
    }

    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background),
        bottomBar = {
            BottomNavigationBar(
                selected = selectedScreen,
                onSelect = { selectedScreen = it }
            )
        }
    ) { innerPadding ->
        when (selectedScreen) {
            "profile" -> ProfileScreen(
                // pass down every piece of state + its setter
                selectedGenres      = genreSelection,
                onGenresChanged     = {
                    genreSelection      = it
                    Log.d("FILTER_LOG", "Selected genres: ${it.filterValues {v -> v}.keys}")
                                      },
                selectedDates    = advancedSelection,
                onDatesChanged   = { advancedSelection   = it },
                selectedNonfiction  = nonfictionSelection,
                onNonfictionChanged = { nonfictionSelection = it },
                selectedFantasy     = fantasySelection,
                onFantasyChanged    = { fantasySelection    = it },
                selectedHorror      = horrorSelection,
                onHorrorChanged     = { horrorSelection     = it },
                selectedScifi       = scifiSelection,
                onScifiChanged      = { scifiSelection      = it },
                selectedRomance     = romanceSelection,
                onRomanceChanged    = { romanceSelection    = it },

                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            "discover" -> DiscoverScreen(
                books   = books,
                onReact = { book, reaction ->
                    // mark seen so filters never bring it back
                    book.key?.let { seenIds.add(it) }

                    // remove it from the discover stack
                    books.remove(book)

                    // add to the right “My Lists” bucket
                    when (reaction) {
                        BookReaction.READ_LIKED      -> likedBooks.add(book)
                        BookReaction.READ_DISLIKED   -> dislikedBooks.add(book)
                        BookReaction.INTERESTED      -> interestedBooks.add(book)
                        BookReaction.NOT_INTERESTED  -> notInterestedBooks.add(book)
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
            "myLists" -> MyListsScreen(
                likedBooks        = likedBooks,
                dislikedBooks     = dislikedBooks,
                interestedBooks   = interestedBooks,
                notInterestedBooks= notInterestedBooks,
                modifier          = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
            )
        }
    }
}