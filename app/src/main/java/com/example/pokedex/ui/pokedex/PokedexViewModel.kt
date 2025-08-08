package com.example.pokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.domain.model.PokemonDetailResponse
import com.example.pokedex.network.PokeApi
import com.example.pokedex.domain.repository.FavoriteRepositoryImpl
import com.example.pokedex.domain.model.PokemonResponse
import kotlinx.coroutines.launch

// TODO: please fix all the lint warnings here
class PokedexViewModel : ViewModel() {
    private val _pokemons = MutableLiveData<PokemonResponse?>()
    val pokemons: MutableLiveData<PokemonResponse?>
        get() = _pokemons
    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse?>()
    val pokemonDetail: MutableLiveData<PokemonDetailResponse?>
        get() = _pokemonDetail
    // TODO: you're not mutating `offset`, do not use `var` here
    // TODO: why are you exposing `offset` and `limit` as a public variables?
    var offset: Int = 0
    private val limit: Int = 5
    val favoriteRepository = FavoriteRepositoryImpl()

    // TODO: i'm really curious about this line. Why did you end-up using an init here?
    //  should it be better to fetch the pokemon list in an event related to the fragment lifecycle?
    init {
        getPokemons(offset, limit)
    }

    fun getPokemons(
        start: Int,
        limit: Int
    ) {
        viewModelScope.launch {
            try {
                _pokemons.value = PokeApi.retrofitService.getPokemons(start, limit)
            } catch (e: Exception) {
                _pokemons.value = null
                // TODO: Are you planning to handle the error and empty states?
                Log.e("ERROR", "could not retrieve from retrofit $e")
            }
        }
    }

    fun getPokemonDetail(name: String) {

        viewModelScope.launch {
            try {
                _pokemonDetail.value = PokeApi.retrofitService.getPokemonDetail(name)
            } catch (e: Exception) {
                _pokemonDetail.value = null
                Log.e("ERROR", "could not retrieve data, $e")
            }
        }
    }
}
