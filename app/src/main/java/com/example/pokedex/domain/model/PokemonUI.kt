package com.example.pokedex.domain.model

// TODO: is it really possible to have nullable images based on the API response?
data class PokemonUI(
    val name: String,
    val isFavorite: Boolean,
    val imageDefault: String?,
    val imageShiny: String?
)
