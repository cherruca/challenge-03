package com.example.pokedex.data

import com.example.pokedex.data.list.ListItem

data class Pokemon(
    val name: String,
    val url: String
)

data class PokemonUI(
    val name: String,
    val url: String,
    val isFavorite: Boolean,
    val image: String?
): ListItem {
    // todo fun getImage() = "https:/.../$name.jpeg"
    override fun getListItemType(): Int =
        if (isFavorite) {
            // todo use the ordinal from the interface
            1
        } else {
            2
        }
}
