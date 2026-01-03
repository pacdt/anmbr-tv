package com.anmfire.tv.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClient {
    private const val BASE_URL = "https://cdn.jsdelivr.net/gh/pacdt/anm-db@main/api_dist/v1/"

    val api: AnmApi by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AnmApi::class.java)
    }
}
