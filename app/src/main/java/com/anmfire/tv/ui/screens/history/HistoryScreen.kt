package com.anmfire.tv.ui.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.LinearProgressIndicator
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
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral300
import com.anmfire.tv.ui.theme.Neutral950

@Composable
fun HistoryScreen(
    onAnimeClick: (String) -> Unit
) {
    val context = LocalContext.current
    val repository = remember { AnimeRepository(context) }
    val history by repository.getAllHistory().collectAsState(initial = emptyList())
    val screenPadding = UiUtils.getScreenPadding()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral950)
            .padding(screenPadding)
    ) {
        Text(
            text = "Histórico",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        LazyColumn(
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(history) { item ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    val anime = Anime(
                        slug = item.slug,
                        title = item.title,
                        type = "TV",
                        images = Images(poster = item.coverUrl, banner = ""),
                        synopsis = null,
                        image = null,
                        genres = null,
                        episodes = null
                    )
                    AnimeCard(anime = anime, onClick = { onAnimeClick(item.slug) })
                    
                    Column(modifier = Modifier.padding(start = 16.dp).weight(1f)) {
                        Text(text = item.title, color = Color.White, fontSize = 20.sp, fontWeight = FontWeight.Bold)
                        Text(text = "Episódio ${item.episodeNumber}", color = Neutral300, fontSize = 16.sp)
                        
                        val progress = if (item.duration > 0) item.position.toFloat() / item.duration.toFloat() else 0f
                        LinearProgressIndicator(
                            progress = progress,
                            color = BrandAccent,
                            trackColor = Neutral300.copy(alpha = 0.3f),
                            modifier = Modifier.padding(top = 8.dp).fillMaxWidth()
                        )
                    }
                }
            }
        }
    }
}
