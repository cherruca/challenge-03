package com.example.pokedex.data

data class FavoritePokemon(
    val name: String
)

interface FavoriteRepository {
    fun isFavorite(name: String): Boolean

    fun toggleFavorite(name: String)
}

class FavoriteRepositoryImpl : FavoriteRepository {
    // todo use set instead of list
    private val favorites = mutableListOf<FavoritePokemon>(FavoritePokemon("bulbasaur"))

    override fun isFavorite(name: String): Boolean = favorites.contains(FavoritePokemon(name))

    override fun toggleFavorite(name: String) {
        if (!isFavorite(name)) {
            favorites.add(FavoritePokemon(name))
        } else {
            favorites.remove(FavoritePokemon(name))
        }
    }
}
