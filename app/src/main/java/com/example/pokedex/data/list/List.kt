package com.example.pokedex.data.list

interface ListItem {
    // TODO: are you planning you use this enum, right?
    enum class PokemonItem(
        val isFavorite: Boolean
    ) {
        Favorite(isFavorite = true),
        NotFavorite(isFavorite = false)
    }

    fun getListItemType(): Int
}
