package com.anmfire.tv.data.model

import com.google.gson.annotations.SerializedName

data class Anime(
    @SerializedName("slug") val slug: String,
    @SerializedName("title") val title: String,
    @SerializedName("title_original") val titleOriginal: String? = null,
    @SerializedName("synopsis") val synopsis: String?,
    @SerializedName("images") val images: Images?,
    @SerializedName("image") val image: String?, // For catalog/genre lists
    @SerializedName("genres") val genres: List<String>?,
    @SerializedName("episodes") val episodes: List<Episode>?,
    @SerializedName("format") val format: String? = "TV",
    @SerializedName("type") val type: String? = "Anime",
    @SerializedName("score") val score: Any? = "N/A", // Can be Double (8.7) or String
    @SerializedName("title_jp") val title_jp: String? = null
) {
    fun getPoster(): String {
        return images?.poster ?: image ?: ""
    }

    fun getScoreString(): String {
        return score?.toString() ?: "N/A"
    }
}

data class Images(
    @SerializedName("poster") val poster: String,
    @SerializedName("banner") val banner: String?
)

data class Episode(
    @SerializedName("numero") val numero: Any? = null, // Can be Int or String
    @SerializedName("number") val number: String? = null,
    @SerializedName("url") val url: String? = null,
    @SerializedName("link") val link: String? = null,
    @SerializedName("nome") val nome: String? = null
) {
    fun getEpisodeNumber(): String {
        val num = numero?.toString() ?: number
        return try {
            val d = num?.toDoubleOrNull()
            if (d != null && d % 1 == 0.0) {
                d.toInt().toString()
            } else {
                num ?: "??"
            }
        } catch (e: Exception) {
            num ?: "??"
        }
    }

    fun getVideoUrl(): String {
        return url ?: link ?: ""
    }
    
    fun getTitle(): String {
        return nome ?: "Episódio ${getEpisodeNumber()}"
    }
}

data class Genre(
    @SerializedName("name") val name: String,
    @SerializedName("slug") val slug: String,
    @SerializedName("count") val count: Int
)

data class GenreResponse(
    @SerializedName("name") val name: String?,
    @SerializedName("slug") val slug: String?,
    @SerializedName("count") val count: Int?,
    @SerializedName("animes") val animes: List<Anime>
)
