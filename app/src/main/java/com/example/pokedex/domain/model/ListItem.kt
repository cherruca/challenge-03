package com.example.pokedex.domain.model

sealed class ListItem {
    abstract val viewType: ViewType

    enum class ViewType(val id: Int) {
        POKEMON_FAVORITE(1),
        POKEMON_NOT_FAVORITE(2);

        companion object {
            fun fromId(id: Int): ViewType = entries.find { it.id == id } ?: POKEMON_NOT_FAVORITE
        }
    }

    data class PokemonItem(
        val pokemonUI: PokemonUI,
        override val viewType: ViewType = if (pokemonUI.isFavorite) ViewType.POKEMON_FAVORITE else ViewType.POKEMON_NOT_FAVORITE
    ) : ListItem()
}