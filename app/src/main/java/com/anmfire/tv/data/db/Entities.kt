package com.anmfire.tv.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorites")
data class Favorite(
    @PrimaryKey val slug: String,
    val title: String,
    val coverUrl: String
)

@Entity(tableName = "watch_history", primaryKeys = ["slug", "episodeNumber"])
data class WatchHistory(
    val slug: String,
    val episodeNumber: String,
    val title: String,
    val coverUrl: String,
    val position: Long,
    val duration: Long,
    val lastWatched: Long = System.currentTimeMillis()
)

@Entity(tableName = "home_cache")
data class HomeCache(
    @PrimaryKey val id: Int = 1,
    val data: String, // JSON list of Anime
    val timestamp: Long
)
