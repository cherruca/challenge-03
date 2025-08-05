package com.example.pokedex.api

import com.example.pokedex.data.PokemonResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://pokeapi.co/api/v2/"

private val moshi =
    Moshi
        .Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

private val retrofit =
    Retrofit
        .Builder()
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(BASE_URL)
        .build()

interface PokeApiService {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: String,
        @Query("limit") limit: String
    ): PokemonResponse
}

object PokeApi {
    val retrofitService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}
