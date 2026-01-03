package com.anmfire.tv.ui.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.anmfire.tv.common.UiUtils
import com.anmfire.tv.data.model.Anime
import com.anmfire.tv.data.model.Genre
import com.anmfire.tv.data.repository.AnimeRepository
import com.anmfire.tv.ui.components.AnimeCard
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral300
import com.anmfire.tv.ui.theme.Neutral800
import com.anmfire.tv.ui.theme.Neutral950

@Composable
fun HomeScreen(
    onAnimeClick: (Anime) -> Unit,
    onGenreClick: (String) -> Unit
) {
    val context = LocalContext.current
    val repository = remember { AnimeRepository(context) }
    var catalog by remember { mutableStateOf<List<Anime>>(emptyList()) }
    var heroAnime by remember { mutableStateOf<Anime?>(null) }
    var genreRails by remember { mutableStateOf<Map<Genre, List<Anime>>>(emptyMap()) }
    var isLoading by remember { mutableStateOf(true) }

    LaunchedEffect(Unit) {
        val loadedCatalog = repository.getCatalog()
        catalog = loadedCatalog
        if (loadedCatalog.isNotEmpty()) {
            heroAnime = loadedCatalog.random()
        }
        
        val genres = repository.getGenreList()
        // Load rails for available genres
        // We limit to first 10 genres to avoid excessive loading for now, 
        // or we could load them progressively.
        val loadedRails = mutableMapOf<Genre, List<Anime>>()
        
        // Prioritize some common genres if they exist
        val prioritized = listOf("Action", "Adventure", "Fantasy", "Sci-Fi", "Comedy", "Drama")
        val sortedGenres = genres.sortedByDescending { it.name in prioritized }

        sortedGenres.take(15).forEach { genre ->
             val animes = repository.getAnimesByGenre(genre.slug).take(10)
             if (animes.isNotEmpty()) {
                 loadedRails[genre] = animes
             }
        }
        
        genreRails = loadedRails
        isLoading = false
    }

    val screenPadding = UiUtils.getScreenPadding()

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(Neutral950), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = BrandAccent)
        }
    } else {
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral950),
            contentPadding = PaddingValues(bottom = screenPadding)
        ) {
            // Hero Section
            item {
                heroAnime?.let { anime ->
                    HeroSection(anime = anime, onClick = { onAnimeClick(anime) })
                }
            }
            
            // Genre Rails
            items(genreRails.toList()) { (genre, animes) ->
                Column(modifier = Modifier.padding(top = 24.dp)) {
                    Text(
                        text = genre.name,
                        color = Color.White,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = screenPadding, vertical = 16.dp)
                    )
                    
                    LazyRow(
                        contentPadding = PaddingValues(horizontal = screenPadding),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        items(animes) { anime ->
                            AnimeCard(anime = anime, onClick = { onAnimeClick(anime) })
                        }
                        
                        item {
                            SeeMoreCard(onClick = { onGenreClick(genre.slug) })
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HeroSection(anime: Anime, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
    ) {
        // Background Image with gradient
        Image(
            painter = rememberAsyncImagePainter(model = anime.images?.poster ?: anime.image), // Ideally banner if available
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color.Transparent,
                            Neutral950
                        ),
                        startY = 0f,
                        endY = 1000f
                    )
                )
        )
        
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.horizontalGradient(
                        colors = listOf(
                            Neutral950.copy(alpha = 0.9f),
                            Color.Transparent
                        ),
                        startX = 0f,
                        endX = 1000f
                    )
                )
        )

        // Content
        Column(
            modifier = Modifier
                .align(Alignment.BottomStart)
                .padding(40.dp)
                .width(500.dp)
        ) {
            Text(
                text = anime.title,
                fontSize = 40.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                lineHeight = 48.sp
            )
            
            Spacer(modifier = Modifier.height(16.dp))
            
            Text(
                text = anime.synopsis ?: "",
                fontSize = 16.sp,
                color = Neutral300,
                maxLines = 3,
                overflow = androidx.compose.ui.text.style.TextOverflow.Ellipsis
            )
            
            Spacer(modifier = Modifier.height(24.dp))
            
            Button(
                onClick = onClick,
                colors = ButtonDefaults.buttonColors(containerColor = BrandAccent),
                shape = RoundedCornerShape(8.dp)
            ) {
                Text(
                    text = "Assistir Agora",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun SeeMoreCard(onClick: () -> Unit) {
    Column(
        modifier = Modifier
            .width(140.dp)
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(8.dp))
            .background(Neutral800)
            .clickable(onClick = onClick)
            .focusable(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Box(
            modifier = Modifier
                .background(BrandAccent, shape = androidx.compose.foundation.shape.CircleShape)
                .padding(12.dp)
        ) {
            Icon(
                imageVector = Icons.Default.ArrowForward,
                contentDescription = "Ver Mais",
                tint = Color.White
            )
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "Ver Mais",
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}
