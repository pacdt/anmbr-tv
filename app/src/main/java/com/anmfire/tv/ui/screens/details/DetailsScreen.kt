package com.anmfire.tv.ui.screens.details

import android.content.Intent
import android.net.Uri
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.GridItemSpan
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.anmfire.tv.common.DeviceUtils
import com.anmfire.tv.common.UiUtils
import com.anmfire.tv.data.db.WatchHistory
import com.anmfire.tv.data.model.Anime
import com.anmfire.tv.data.model.Episode
import com.anmfire.tv.data.repository.AnimeRepository
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral300
import com.anmfire.tv.ui.theme.Neutral400
import com.anmfire.tv.ui.theme.Neutral800
import com.anmfire.tv.ui.theme.Neutral950
import kotlinx.coroutines.launch

@Composable
fun DetailsScreen(
    slug: String,
    onEpisodeClick: (String, String, String, String) -> Unit, // url, episode, title, cover
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val repository = remember { AnimeRepository(context) }
    var anime by remember { mutableStateOf<Anime?>(null) }
    var isLoading by remember { mutableStateOf(true) }
    val historyList by repository.getHistoryForAnime(slug).collectAsState(initial = emptyList())
    val isFavorite by repository.isFavorite(slug).collectAsState(initial = false)
    val scope = rememberCoroutineScope()

    LaunchedEffect(slug) {
        anime = repository.getAnimeDetails(slug)
        isLoading = false
    }

    if (isLoading) {
        Box(modifier = Modifier.fillMaxSize().background(Neutral950), contentAlignment = Alignment.Center) {
            CircularProgressIndicator(color = BrandAccent)
        }
    } else {
        val isTv = remember { DeviceUtils.isTv(context) }
        val screenPadding = UiUtils.getScreenPadding()
        
        anime?.let { data ->
            Box(modifier = Modifier.fillMaxSize().background(Neutral950)) {
                // Backdrop (Fixed background)
                Box(modifier = Modifier.fillMaxWidth().height(400.dp)) {
                    Image(
                        painter = rememberAsyncImagePainter(data.images?.banner ?: data.getPoster()),
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize(),
                        contentScale = ContentScale.Crop,
                        alpha = 0.2f
                    )
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(Neutral950.copy(alpha = 0.1f), Neutral950),
                                    startY = 0f,
                                    endY = Float.POSITIVE_INFINITY
                                )
                            )
                    )
                }

                // Main Content
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(minSize = 100.dp),
                    contentPadding = PaddingValues(screenPadding),
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp),
                    modifier = Modifier.fillMaxSize()
                ) {
                    // Header Section
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        if (isTv) {
                            // TV Layout (Row)
                            Row(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 32.dp),
                                horizontalArrangement = Arrangement.spacedBy(40.dp)
                            ) {
                                // Poster & Actions
                                Column(
                                    modifier = Modifier.width(250.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    AnimePoster(data.getPoster(), data.title, Modifier.width(250.dp))
                                    Spacer(modifier = Modifier.height(24.dp))
                                    ActionsButton(
                                        isFavorite = isFavorite,
                                        onToggleFavorite = {
                                            if (isFavorite) scope.launch { repository.removeFavorite(slug) }
                                            else scope.launch { repository.addFavorite(data) }
                                        },
                                        onTrailerClick = {
                                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=${data.title} trailer"))
                                            context.startActivity(intent)
                                        }
                                    )
                                }
                                
                                // Info
                                Column(modifier = Modifier.weight(1f)) {
                                    AnimeInfo(data)
                                }
                            }
                        } else {
                            // Mobile Layout (Column)
                            Column(
                                modifier = Modifier.fillMaxWidth().padding(bottom = 24.dp),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                AnimePoster(data.getPoster(), data.title, modifier = Modifier.width(180.dp))
                                Spacer(modifier = Modifier.height(16.dp))
                                Text(
                                    text = data.title,
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White,
                                    textAlign = androidx.compose.ui.text.style.TextAlign.Center
                                )
                                Spacer(modifier = Modifier.height(16.dp))
                                ActionsButton(
                                    isFavorite = isFavorite,
                                    onToggleFavorite = {
                                        if (isFavorite) scope.launch { repository.removeFavorite(slug) }
                                        else scope.launch { repository.addFavorite(data) }
                                    },
                                    onTrailerClick = {
                                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/results?search_query=${data.title} trailer"))
                                        context.startActivity(intent)
                                    }
                                )
                                Spacer(modifier = Modifier.height(24.dp))
                                AnimeInfo(data, centerText = true)
                            }
                        }
                    }

                    // Episodes Header
                    item(span = { GridItemSpan(maxLineSpan) }) {
                        Text(
                            text = "Episódios",
                            fontSize = 24.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            modifier = Modifier.padding(bottom = 8.dp)
                        )
                    }

                    // Episodes List
                    val episodes = data.episodes ?: emptyList()
                    items(episodes) { episode ->
                        val history = historyList.find { it.episodeNumber == episode.getEpisodeNumber() }
                        EpisodeItem(
                            episode = episode,
                            history = history,
                            onClick = { 
                                val videoUrl = episode.getVideoUrl()
                                if (videoUrl.isNotEmpty()) {
                                    onEpisodeClick(
                                        videoUrl, 
                                        episode.getEpisodeNumber(), 
                                        data.title, 
                                        data.getPoster()
                                    ) 
                                }
                            }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Tag(text: String, isGenre: Boolean = false) {
    Box(
        modifier = Modifier
            .background(
                if (isGenre) BrandAccent.copy(alpha = 0.2f) else Neutral800, 
                RoundedCornerShape(4.dp)
            )
            .padding(horizontal = 8.dp, vertical = 4.dp)
    ) {
        Text(
            text = text,
            color = if (isGenre) BrandAccent else Neutral300,
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun EpisodeItem(
    episode: Episode,
    history: WatchHistory?,
    onClick: () -> Unit
) {
    var isFocused by remember { mutableStateOf(false) }
    
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(8.dp))
            .background(if (isFocused) BrandAccent else Neutral800)
            .clickable(onClick = onClick)
            .onFocusChanged { isFocused = it.isFocused }
            .focusable()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = "EP ${episode.getEpisodeNumber()}",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.width(80.dp)
        )
        
        Spacer(modifier = Modifier.width(16.dp))
        
        Column(modifier = Modifier.weight(1f)) {
            // Nome do episódio
            Text(
                text = episode.nome ?: "Episódio ${episode.getEpisodeNumber()}",
                color = if (isFocused) Color.White.copy(alpha = 0.9f) else Neutral300,
                fontSize = 14.sp,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            
            if (history != null && history.duration > 0) {
                Spacer(modifier = Modifier.height(4.dp))
                val progress = history.position.toFloat() / history.duration.toFloat()
                LinearProgressIndicator(
                    progress = progress,
                    color = if (isFocused) Color.White else BrandAccent,
                    trackColor = Neutral400.copy(alpha = 0.3f),
                    modifier = Modifier.height(4.dp).fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun AnimePoster(url: String?, title: String, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(url),
        contentDescription = title,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .aspectRatio(2f / 3f)
            .clip(RoundedCornerShape(12.dp))
            .border(2.dp, Neutral800, RoundedCornerShape(12.dp))
    )
}

@Composable
fun ActionsButton(
    isFavorite: Boolean, 
    onToggleFavorite: () -> Unit,
    onTrailerClick: () -> Unit
) {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Button(
            onClick = onToggleFavorite,
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isFavorite) Neutral800 else BrandAccent
            ),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                contentDescription = "Favoritar",
                tint = Color.White
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = if (isFavorite) "Remover" else "Favoritos")
        }

        Button(
            onClick = onTrailerClick,
            colors = ButtonDefaults.buttonColors(containerColor = Neutral800),
            shape = RoundedCornerShape(8.dp),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Ver Trailer", color = Color.White)
        }
    }
}

@Composable
fun AnimeInfo(data: Anime, centerText: Boolean = false) {
    val alignment = if (centerText) Alignment.CenterHorizontally else Alignment.Start
    val textAlign = if (centerText) androidx.compose.ui.text.style.TextAlign.Center else androidx.compose.ui.text.style.TextAlign.Start
    var isExpanded by remember { mutableStateOf(false) }

    Column(horizontalAlignment = alignment) {
        if (!centerText) {
            Text(
                text = data.title,
                fontSize = 36.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        
        Row(horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            Tag(data.format ?: "TV")
            Tag(data.type ?: "Anime")
            Tag("Nota: ${data.getScoreString()}")
        }
        
        Spacer(modifier = Modifier.height(16.dp))
        
        // Genre Tags
        Row(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
            data.genres?.take(4)?.forEach { genre ->
                Tag(genre, isGenre = true)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))
        
        Column(
             modifier = Modifier.animateContentSize().clickable { isExpanded = !isExpanded },
             horizontalAlignment = alignment
        ) {
            Text(
                text = data.synopsis ?: "Sem sinopse.",
                color = Neutral300,
                fontSize = 16.sp,
                maxLines = if (isExpanded) Int.MAX_VALUE else 4,
                overflow = TextOverflow.Ellipsis,
                textAlign = textAlign
            )
            
            if (!isExpanded && (data.synopsis?.length ?: 0) > 100) {
                 Text(
                    text = "Ler mais",
                    color = BrandAccent,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(top = 8.dp)
                 )
            }
        }
    }
}
