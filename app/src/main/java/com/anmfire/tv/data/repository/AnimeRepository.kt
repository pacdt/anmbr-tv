package com.anmfire.tv.data.repository

import android.content.Context
import com.anmfire.tv.data.api.RetrofitClient
import com.anmfire.tv.data.db.AppDatabase
import com.anmfire.tv.data.db.Favorite
import com.anmfire.tv.data.db.HomeCache
import com.anmfire.tv.data.db.WatchHistory
import com.anmfire.tv.data.model.Anime
import com.anmfire.tv.data.model.Genre
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.concurrent.TimeUnit

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimeRepository(context: Context? = null) {
    private val api = RetrofitClient.api
    private val db = context?.let { AppDatabase.getDatabase(it) }
    private val gson = Gson()

    suspend fun getCatalog(): List<Anime> = withContext(Dispatchers.IO) {
        val cacheDao = db?.homeCacheDao()
        val currentTime = System.currentTimeMillis()
        val oneWeekMillis = TimeUnit.DAYS.toMillis(7)

        // Try load from cache
        try {
            val cache = cacheDao?.getCache()
            if (cache != null) {
                if (currentTime - cache.timestamp < oneWeekMillis) {
                    val type = object : TypeToken<List<Anime>>() {}.type
                    val cachedList: List<Anime> = gson.fromJson(cache.data, type)
                    if (cachedList.isNotEmpty()) return@withContext cachedList
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            // Continue to API if cache fails
        }

        // Fetch from API
        try {
            val animes = api.getCatalog()
            
            // Save to cache
            if (animes.isNotEmpty()) {
                try {
                    val json = gson.toJson(animes)
                    cacheDao?.insertCache(HomeCache(data = json, timestamp = currentTime))
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            
            animes
        } catch (e: Exception) {
            e.printStackTrace()
            // Fallback to expired cache if available
            try {
                val cache = cacheDao?.getCache()
                if (cache != null) {
                    val type = object : TypeToken<List<Anime>>() {}.type
                    gson.fromJson(cache.data, type)
                } else {
                    emptyList()
                }
            } catch (ex: Exception) {
                emptyList()
            }
        }
    }

    suspend fun getAnimeDetails(slug: String): Anime? {
        return try {
            api.getAnimeDetails(slug)
        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    suspend fun getGenreList(): List<Genre> {
        return try {
            api.getGenreList().sortedBy { it.name }
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    suspend fun getAnimesByGenre(genre: String): List<Anime> {
        return try {
            val slug = genre.lowercase().trim().replace(" ", "-")
            val response = api.getAnimesByGenre(slug)
            response.animes
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    // DB Methods
    fun getAllFavorites(): Flow<List<Favorite>> = db?.favoriteDao()?.getAllFavorites() ?: flowOf(emptyList())

    fun isFavorite(slug: String): Flow<Boolean> = db?.favoriteDao()?.isFavorite(slug) ?: flowOf(false)

    suspend fun addFavorite(anime: Anime) {
        db?.favoriteDao()?.insertFavorite(Favorite(anime.slug, anime.title, anime.images?.poster ?: ""))
    }

    suspend fun removeFavorite(slug: String) {
        db?.favoriteDao()?.deleteFavoriteBySlug(slug)
    }

    fun getAllHistory(): Flow<List<WatchHistory>> = db?.watchHistoryDao()?.getAllHistory() ?: flowOf(emptyList())

    suspend fun saveHistory(history: WatchHistory) {
        db?.watchHistoryDao()?.insertOrUpdateHistory(history)
    }
    
    fun getHistoryForAnime(slug: String): Flow<List<WatchHistory>> {
        return db?.watchHistoryDao()?.getHistoryForAnime(slug) ?: flowOf(emptyList())
    }
}
