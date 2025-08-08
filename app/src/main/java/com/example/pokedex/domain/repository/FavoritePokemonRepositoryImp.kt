package com.example.pokedex.domain.repository

import com.example.pokedex.domain.model.FavoritePokemon
import com.example.pokedex.domain.repository.FavoriteRepository

class FavoriteRepositoryImpl: FavoriteRepository {
    private val favorites = mutableSetOf<FavoritePokemon>(FavoritePokemon("bulbasaur"))

    override fun isFavorite(name: String): Boolean = favorites.contains(
        FavoritePokemon(name)
    )

    override fun toggleFavorite(name: String) {
        if (!isFavorite(name)) {
            favorites.add(FavoritePokemon(name))
        } else {
            favorites.remove(FavoritePokemon(name))
        }
    }
}