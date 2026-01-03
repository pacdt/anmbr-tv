package com.anmfire.tv.ui.screens.favorites

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anmfire.tv.common.UiUtils
import com.anmfire.tv.data.model.Anime
import com.anmfire.tv.data.model.Images
import com.anmfire.tv.data.repository.AnimeRepository
import com.anmfire.tv.ui.components.AnimeCard
import com.anmfire.tv.ui.theme.Neutral950

@Composable
fun FavoritesScreen(
    onAnimeClick: (String) -> Unit
) {
    val context = LocalContext.current
    val repository = remember { AnimeRepository(context) }
    val favorites by repository.getAllFavorites().collectAsState(initial = emptyList())
    val screenPadding = UiUtils.getScreenPadding()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral950)
            .padding(screenPadding)
    ) {
        Text(
            text = "Favoritos",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Adaptive(140.dp),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(favorites) { fav ->
                // Map Favorite entity to Anime model for the card
                val anime = Anime(
                    slug = fav.slug,
                    title = fav.title,
                    type = "TV",
                    images = Images(poster = fav.coverUrl, banner = ""),
                    synopsis = null,
                    image = null,
                    genres = null,
                    episodes = null
                )
                AnimeCard(anime = anime, onClick = { onAnimeClick(fav.slug) })
            }
        }
    }
}
