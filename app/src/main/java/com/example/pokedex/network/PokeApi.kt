package com.example.pokedex.network

import com.example.pokedex.network.PokeApiService

object PokeApi {
    val retrofitService: PokeApiService by lazy {
        retrofit.create(PokeApiService::class.java)
    }
}