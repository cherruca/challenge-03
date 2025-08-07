package com.example.pokedex.data

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonUI(
    val name: String,
    val url: String,
    val favorite: Boolean,
    val image: String?
) {
    // todo fun getImage() = "https:/.../$name.jpeg"
}
