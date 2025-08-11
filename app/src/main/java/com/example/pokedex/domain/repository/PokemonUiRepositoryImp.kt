package com.example.pokedex.domain.repository

import com.example.pokedex.domain.model.PokemonDetailResponse
import com.example.pokedex.domain.model.PokemonUI

class PokemonUiRepositoryImp: PokemonUiRepository {
    var pokemonsUI: MutableSet<PokemonUI> = PokemonUiDataSource.pokemonsUI
    private val favoriteRepository = FavoriteRepositoryImpl()

    override fun addPokemons(pokemonDetailResponse: PokemonDetailResponse?) {
        val newPokemonUI = pokemonDetailResponse?.let {
            PokemonUI(
                name = it.name,
                isFavorite = favoriteRepository.isFavorite(pokemonDetailResponse.name),
                imageDefault = pokemonDetailResponse.sprites.frontDefault,
                imageShiny = pokemonDetailResponse.sprites.frontShiny
            )

        }
        if (newPokemonUI != null) {
            pokemonsUI.add(newPokemonUI)
        }
    }
}