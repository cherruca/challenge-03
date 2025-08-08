package com.example.pokedex.domain.repository

class FavoriteRepositoryImpl: FavoriteRepository {
    private var favorites: MutableSet<String> = FavoritesDataSource.favorites

    override fun isFavorite(name: String): Boolean = favorites.contains(
        name
    )

    override fun toggleFavorite(name: String) {
        if (!isFavorite(name)) {
            favorites.add(name)
        } else {
            favorites.remove(name)
        }
    }
}
