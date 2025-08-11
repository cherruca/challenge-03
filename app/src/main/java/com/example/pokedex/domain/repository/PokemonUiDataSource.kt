package com.example.pokedex.domain.repository

import com.example.pokedex.domain.model.PokemonDetailResponse
import com.example.pokedex.domain.model.PokemonUI

object PokemonUiDataSource {
    var pokemonsUI: MutableSet<PokemonUI> = mutableSetOf()
}