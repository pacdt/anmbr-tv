package com.anmfire.tv.data.api

import com.anmfire.tv.data.model.Anime
import com.anmfire.tv.data.model.Genre
import com.anmfire.tv.data.model.GenreResponse
import com.google.gson.JsonElement
import retrofit2.http.GET
import retrofit2.http.Path

interface AnmApi {
    @GET("animes/all.json")
    suspend fun getCatalog(): List<Anime>

    @GET("animes/{slug}.json")
    suspend fun getAnimeDetails(@Path("slug") slug: String): Anime

    @GET("genres/{genre}.json")
    suspend fun getAnimesByGenre(@Path("genre") genre: String): GenreResponse

    @GET("genres/list.json")
    suspend fun getGenreList(): List<Genre>
}
