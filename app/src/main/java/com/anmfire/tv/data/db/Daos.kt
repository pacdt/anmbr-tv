package com.anmfire.tv.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface FavoriteDao {
    @Query("SELECT * FROM favorites")
    fun getAllFavorites(): Flow<List<Favorite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavorite(favorite: Favorite)

    @Delete
    suspend fun deleteFavorite(favorite: Favorite)

    @Query("DELETE FROM favorites WHERE slug = :slug")
    suspend fun deleteFavoriteBySlug(slug: String)

    @Query("SELECT EXISTS(SELECT 1 FROM favorites WHERE slug = :slug)")
    fun isFavorite(slug: String): Flow<Boolean>
}

@Dao
interface WatchHistoryDao {
    @Query("SELECT * FROM watch_history ORDER BY lastWatched DESC")
    fun getAllHistory(): Flow<List<WatchHistory>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateHistory(history: WatchHistory)

    @Query("SELECT * FROM watch_history WHERE slug = :slug")
    fun getHistoryForAnime(slug: String): Flow<List<WatchHistory>>
    
    @Query("DELETE FROM watch_history")
    suspend fun clearHistory()
}

@Dao
interface HomeCacheDao {
    @Query("SELECT * FROM home_cache WHERE id = 1")
    suspend fun getCache(): HomeCache?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCache(cache: HomeCache)

    @Query("DELETE FROM home_cache")
    suspend fun clearCache()
}
