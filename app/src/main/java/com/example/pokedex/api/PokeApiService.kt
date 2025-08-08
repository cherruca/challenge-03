package com.example.pokedex.api

import com.example.pokedex.data.PokemonDetailResponse
import com.example.pokedex.data.PokemonResponse
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

// TODO: please separate this into several files, group instances in a way that makes sense
// reference repo: https://github.com/udacity/andfun-kotlin-mars-real-estate/blob/master/app/src/main/java/com/example/android/marsrealestate/network/MarsApiService.kt

private const val BASE_URL = "https://pokeapi.co/api/v2/" // todo: move to local properties

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
        @Query("offset") offset: Int?,
        @Query("limit") limit: Int?
    ): PokemonResponse

    @GET("pokemon/{name}")
    suspend fun getPokemonDetail(
        @Path("name") name: String
    ): PokemonDetailResponse
}

object PokeApi {
    val retrofitService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}
