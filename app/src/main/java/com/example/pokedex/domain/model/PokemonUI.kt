package com.example.pokedex.domain.model

import com.example.pokedex.data.list.ListItem

// TODO: is it really possible to have nullable images based on the API response?
data class PokemonUI(
    val name: String,
    val url: String,
    val isFavorite: Boolean,
    val imageDefault: String?,
    val imageShiny: String?
): ListItem {
    // todo fun getImage() = "https:/.../$name.jpeg"
    override fun getListItemType(): Int =
    // TODO: nope, those magic numbers are not readable at all.
        //  Please use an interface here.
        if (isFavorite) {
            // todo use the ordinal from the interface
            1
        } else {
            2
        }
}
