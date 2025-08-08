package com.example.pokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.PokeApi
import com.example.pokedex.data.FavoriteRepository
import com.example.pokedex.data.FavoriteRepositoryImpl
import com.example.pokedex.data.PokemonResponse
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {
    private val _pokemons = MutableLiveData<PokemonResponse?>()
    val pokemons: MutableLiveData<PokemonResponse?>
        get() = _pokemons
    var offset: Int = 0
    val limit: Int = 5
    val favoriteRepository: FavoriteRepository = FavoriteRepositoryImpl()

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
                Log.e("ERROR", "could not retrieve from retrofit $e")
            }
        }
    }
}
