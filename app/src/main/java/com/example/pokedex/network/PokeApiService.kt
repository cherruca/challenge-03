package com.example.pokedex.network

import com.example.pokedex.data.PokemonDetailResponse
import com.example.pokedex.data.PokemonResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokeApiService {
    @GET("pokemon/")
    suspend fun getPokemons(
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailResponse
}