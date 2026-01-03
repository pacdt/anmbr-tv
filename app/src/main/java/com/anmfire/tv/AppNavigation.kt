package com.anmfire.tv

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.anmfire.tv.ui.screens.details.DetailsScreen
import com.anmfire.tv.ui.screens.favorites.FavoritesScreen
import com.anmfire.tv.ui.screens.genre.GenreScreen
import com.anmfire.tv.ui.screens.history.HistoryScreen
import com.anmfire.tv.ui.screens.home.HomeScreen
import com.anmfire.tv.ui.screens.player.PlayerScreen
import com.anmfire.tv.ui.screens.search.SearchScreen
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun AppNavigation(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "home",
        modifier = modifier
    ) {
        composable("home") {
            HomeScreen(
                onAnimeClick = { anime ->
                    navController.navigate("details/${anime.slug}")
                },
                onGenreClick = { genre ->
                    navController.navigate("genre/$genre")
                }
            )
        }
        composable("search") {
            SearchScreen(
                onAnimeClick = { slug ->
                    navController.navigate("details/$slug")
                }
            )
        }
        composable("favorites") {
            FavoritesScreen(
                onAnimeClick = { slug ->
                    navController.navigate("details/$slug")
                }
            )
        }
        composable("history") {
            HistoryScreen(
                onAnimeClick = { slug ->
                    navController.navigate("details/$slug")
                }
            )
        }
        composable(
            "genre/{genreId}",
            arguments = listOf(navArgument("genreId") { type = NavType.StringType })
        ) { backStackEntry ->
            val genreId = backStackEntry.arguments?.getString("genreId") ?: ""
            GenreScreen(
                genreId = genreId,
                onAnimeClick = { slug ->
                    navController.navigate("details/$slug")
                }
            )
        }
        composable(
            "details/{slug}",
            arguments = listOf(navArgument("slug") { type = NavType.StringType })
        ) { backStackEntry ->
            val slug = backStackEntry.arguments?.getString("slug") ?: ""
            DetailsScreen(
                slug = slug,
                onEpisodeClick = { url, episode, title, cover ->
                    val encodedUrl = URLEncoder.encode(url, StandardCharsets.UTF_8.toString())
                    val encodedTitle = URLEncoder.encode(title, StandardCharsets.UTF_8.toString())
                    val encodedCover = URLEncoder.encode(cover, StandardCharsets.UTF_8.toString())
                    navController.navigate("player/$encodedUrl/$slug/$episode/$encodedTitle/$encodedCover")
                },
                onBack = { navController.popBackStack() }
            )
        }
        composable(
            "player/{videoUrl}/{slug}/{episodeNumber}/{title}/{coverUrl}",
            arguments = listOf(
                navArgument("videoUrl") { type = NavType.StringType },
                navArgument("slug") { type = NavType.StringType },
                navArgument("episodeNumber") { type = NavType.StringType },
                navArgument("title") { type = NavType.StringType },
                navArgument("coverUrl") { type = NavType.StringType }
            )
        ) { backStackEntry ->
            val videoUrl = backStackEntry.arguments?.getString("videoUrl") ?: ""
            val slug = backStackEntry.arguments?.getString("slug") ?: ""
            val episodeNumber = backStackEntry.arguments?.getString("episodeNumber") ?: ""
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val coverUrl = backStackEntry.arguments?.getString("coverUrl") ?: ""
            
            PlayerScreen(
                videoUrl = videoUrl,
                slug = slug,
                episodeNumber = episodeNumber,
                animeTitle = title,
                coverUrl = coverUrl,
                onBack = { navController.popBackStack() }
            )
        }
    }
}
