package com.example.pokedex.domain.repository

interface FavoriteRepository {
    fun isFavorite(name: String): Boolean

    fun toggleFavorite(name: String)
}