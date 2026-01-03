package com.anmfire.tv.ui.screens.search

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.anmfire.tv.common.UiUtils
import com.anmfire.tv.data.model.Anime
import com.anmfire.tv.data.repository.AnimeRepository
import com.anmfire.tv.ui.components.AnimeCard
import com.anmfire.tv.ui.theme.BrandAccent
import com.anmfire.tv.ui.theme.Neutral800
import com.anmfire.tv.ui.theme.Neutral950

import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.NativeKeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.nativeKeyCode
import androidx.compose.ui.input.key.onPreviewKeyEvent

@Composable
fun SearchScreen(
    onAnimeClick: (String) -> Unit
) {
    val context = LocalContext.current
    val repository = remember { AnimeRepository(context) }
    var query by remember { mutableStateOf("") }
    var results by remember { mutableStateOf<List<Anime>>(emptyList()) }
    var allAnimes by remember { mutableStateOf<List<Anime>>(emptyList()) }
    var isLoading by remember { mutableStateOf(true) }
    
    val searchFocusRequester = remember { FocusRequester() }
    val gridFocusRequester = remember { FocusRequester() }

    LaunchedEffect(Unit) {
        allAnimes = repository.getCatalog()
        isLoading = false
    }

    LaunchedEffect(query) {
        if (query.isNotEmpty()) {
            results = allAnimes.filter { 
                it.title.contains(query, ignoreCase = true) || 
                (it.title_jp?.contains(query, ignoreCase = true) == true)
            }
        } else {
            results = emptyList()
        }
    }

    val screenPadding = UiUtils.getScreenPadding()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Neutral950)
            .padding(screenPadding)
    ) {
        Text(
            text = "Busca",
            color = Color.White,
            fontSize = 32.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 24.dp)
        )
        
        BasicTextField(
            value = query,
            onValueChange = { query = it },
            textStyle = TextStyle(color = Color.White, fontSize = 20.sp),
            cursorBrush = SolidColor(BrandAccent),
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Neutral800)
                        .padding(16.dp)
                ) {
                    if (query.isEmpty()) {
                        Text("Digite o nome do anime...", color = Color.Gray)
                    }
                    innerTextField()
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(searchFocusRequester)
                .onPreviewKeyEvent {
                    if (it.key == Key.DirectionDown) {
                        if (results.isNotEmpty()) {
                            gridFocusRequester.requestFocus()
                            true
                        } else {
                            false
                        }
                    } else {
                        false
                    }
                }
        )
        
        LaunchedEffect(Unit) {
            searchFocusRequester.requestFocus()
        }

        if (isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator(color = BrandAccent)
            }
        } else {
            if (results.isEmpty() && query.isNotEmpty()) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text("Nenhum resultado encontrado.", color = Color.Gray, fontSize = 18.sp)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Adaptive(140.dp),
                    contentPadding = PaddingValues(top = 24.dp),
                    verticalArrangement = Arrangement.spacedBy(24.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.focusRequester(gridFocusRequester)
                ) {
                    items(results) { anime ->
                        AnimeCard(anime = anime, onClick = { onAnimeClick(anime.slug) })
                    }
                }
            }
        }
    }
}
