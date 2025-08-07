package com.example.pokedex.data.list

interface ListItem {
    enum class PokemonItem(
        val isFavorite: Boolean
    ) {
        Favorite(isFavorite = true),
        NotFavorite(isFavorite = false)
    }

    fun getListItemType(): Int
}
