package com.example.pokedex.data

// TODO: based on the API response, is it possible to have a nullable result?
// TODO: I recommend to use default values as null
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Pokemon>?
)
