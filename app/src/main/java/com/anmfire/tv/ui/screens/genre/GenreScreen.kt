package com.anmfire.tv.ui.screens.genre

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anmfire.tv.common.UiUtils
import com.anmfire.tv.data.model.Anime
import com.anmfire.tv.data.repository.AnimeRepository
import com.anmfire.tv.ui.components.AnimeCard
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral950

@Composable
fun GenreScreen(
    genreId: String,
    onAnimeClick: (String) -> Unit
) {
    val context = LocalContext.current
    val repository = remember { AnimeRepository(context) }
    var animes by remember { mutableStateOf<List<Anime>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }

    val displayGenre = remember(genreId) {
        genreId.replace("-", " ").split(" ").joinToString(" ") { it.replaceFirstChar { char -> char.uppercase() } }
    }

    LaunchedEffect(genreId) {
        animes = repository.getAnimesByGenre(genreId)
        isLoading = false
    }

    val screenPadding = UiUtils.getScreenPadding()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral950)
            .padding(screenPadding)
    ) {
        Text(
            text = displayGenre,
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = BrandAccent)
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Adaptive(140.dp),
                contentPadding = PaddingValues(16.dp),
                verticalArrangement = Arrangement.spacedBy(24.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(animes) { anime ->
                    AnimeCard(anime = anime, onClick = { onAnimeClick(anime.slug) })
                }
            }
        }
    }
}
