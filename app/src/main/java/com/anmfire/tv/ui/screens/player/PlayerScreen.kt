package com.anmfire.tv.ui.screens.player

import android.app.Activity
import android.content.pm.ActivityInfo
import android.net.Uri
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.FrameLayout
import androidx.annotation.OptIn
import androidx.compose.foundation.background
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.media3.common.MediaItem
import androidx.media3.common.Player
import androidx.media3.common.util.UnstableApi
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.ProgressiveMediaSource
import androidx.media3.ui.PlayerView
import com.anmfire.tv.data.db.WatchHistory
import com.anmfire.tv.data.repository.AnimeRepository
import com.anmfire.tv.data.util.VideoExtractor
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral950
import kotlinx.coroutines.launch

@OptIn(UnstableApi::class)
@Composable
fun PlayerScreen(
    videoUrl: String,
    slug: String,
    episodeNumber: String,
    animeTitle: String,
    coverUrl: String,
    onBack: () -> Unit
) {
    val context = LocalContext.current
    val repository = remember { AnimeRepository(context) }
    val scope = rememberCoroutineScope()
    var resolvedUrl by remember { mutableStateOf<String?>(null) }
    var isResolving by remember { mutableStateOf(true) }
    
    val focusRequester = remember { FocusRequester() }

    val exoPlayer = remember {
        ExoPlayer.Builder(context).build().apply {
            playWhenReady = true
        }
    }

    // Save progress helper
    fun saveProgress() {
        // Save even if duration is 0 (some streams don't report duration immediately)
        // But position must be > 0 to be worth saving
        if (exoPlayer.currentPosition > 0) {
            val currentPos = exoPlayer.currentPosition
            val currentDur = if (exoPlayer.duration > 0) exoPlayer.duration else 0
            
            scope.launch {
                try {
                    repository.saveHistory(
                        WatchHistory(
                            slug = slug,
                            episodeNumber = episodeNumber,
                            title = animeTitle,
                            coverUrl = coverUrl,
                            position = currentPos,
                            duration = currentDur,
                            lastWatched = System.currentTimeMillis()
                        )
                    )
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }

    LaunchedEffect(videoUrl) {
        if (videoUrl.isNotEmpty()) {
            isResolving = true
            resolvedUrl = VideoExtractor.extractVideoUrl(videoUrl)
            isResolving = false
        }
    }

    LaunchedEffect(resolvedUrl) {
        resolvedUrl?.let { url ->
            val userAgent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/120.0.0.0 Safari/537.36"
            val dataSourceFactory = DefaultHttpDataSource.Factory()
                .setUserAgent(userAgent)
                .setAllowCrossProtocolRedirects(true)
            
            val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
                .createMediaSource(MediaItem.fromUri(Uri.parse(url)))
            
            exoPlayer.setMediaSource(mediaSource)
            exoPlayer.prepare()
        }
    }

    LaunchedEffect(Unit) {
        val activity = context as? Activity
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE
    }

    DisposableEffect(Unit) {
        val listener = object : Player.Listener {
            override fun onIsPlayingChanged(isPlaying: Boolean) {
                if (!isPlaying) {
                    saveProgress()
                }
            }
            
            override fun onPlaybackStateChanged(playbackState: Int) {
                if (playbackState == Player.STATE_ENDED) {
                    saveProgress()
                }
            }
        }
        
        exoPlayer.addListener(listener)

        onDispose {
            saveProgress()
            exoPlayer.removeListener(listener)
            exoPlayer.release()
            
            val activity = context as? Activity
            activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        }
    }

    if (isResolving) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Neutral950),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(color = BrandAccent)
            Text(
                text = "Carregando vídeo...",
                color = Color.White,
                modifier = Modifier.padding(top = 64.dp)
            )
        }
    } else {
        Box(modifier = Modifier.fillMaxSize()) {
            AndroidView(
                factory = {
                    PlayerView(context).apply {
                        player = exoPlayer
                        layoutParams = FrameLayout.LayoutParams(MATCH_PARENT, MATCH_PARENT)
                        useController = true // Show playback controls
                        controllerShowTimeoutMs = 5000
                        keepScreenOn = true
                        
                        // Ensure it can take focus for D-pad controls
                        isFocusable = true
                        isFocusableInTouchMode = true
                        requestFocus()
                    }
                },
                modifier = Modifier
                    .fillMaxSize()
                    .focusRequester(focusRequester)
                    .focusable()
            )
        }
        
        LaunchedEffect(Unit) {
            focusRequester.requestFocus()
        }
    }
}
