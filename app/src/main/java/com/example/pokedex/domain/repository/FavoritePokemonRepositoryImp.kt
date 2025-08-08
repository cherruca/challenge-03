package com.example.pokedex.domain.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData

class FavoriteRepositoryImpl: FavoriteRepository {
    private var favorites: MutableSet<String> = FavoritesDataSource.favorites

    override fun isFavorite(name: String): Boolean = favorites.contains(
        name
    ) ?: false

    override fun toggleFavorite(name: String) {
        if (!isFavorite(name)) {
            favorites.add(name)
        } else {
            favorites.remove(name)
        }
        Log.d("FAVORITES", favorites.toString())
    }
}
