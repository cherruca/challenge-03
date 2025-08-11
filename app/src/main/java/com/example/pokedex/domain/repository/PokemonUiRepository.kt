package com.example.pokedex.domain.repository

import com.example.pokedex.domain.model.PokemonDetailResponse

interface PokemonUiRepository {
    fun addPokemons(pokemonDetailResponse: PokemonDetailResponse?)
}