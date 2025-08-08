package com.example.pokedex.data

// TODO: fix all the linter warnings
// TODO: please, separate your classes into single files.
//  This is hard to read :crying_blood:
data class FavoritePokemon(
    val name: String
)

interface FavoriteRepository {
    fun isFavorite(name: String): Boolean

    fun toggleFavorite(name: String)
}

class FavoriteRepositoryImpl : FavoriteRepository {
    private val favorites = mutableSetOf<FavoritePokemon>(FavoritePokemon("bulbasaur"))

    override fun isFavorite(name: String): Boolean = favorites.contains(FavoritePokemon(name))

    override fun toggleFavorite(name: String) {
        if (!isFavorite(name)) {
            favorites.add(FavoritePokemon(name))
        } else {
            favorites.remove(FavoritePokemon(name))
        }
    }
}
