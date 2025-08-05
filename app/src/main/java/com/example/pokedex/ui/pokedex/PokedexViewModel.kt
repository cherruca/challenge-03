package com.example.pokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.PokeApi
import com.example.pokedex.data.PokemonResponse
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {
    private val _pokemons = MutableLiveData<PokemonResponse?>()
    val pokemons: MutableLiveData<PokemonResponse?>
        get() = _pokemons

    init {
        getPokemons("0", "5")
    }

    private fun getPokemons(
        start: String,
        limit: String
    ) {
        viewModelScope.launch {
            try {
                _pokemons.value = PokeApi.retrofitService.getPokemons(start, limit)
            } catch (e: Exception) {
                _pokemons.value = null
                Log.d("ERROR", "could not retrieve from retrofit $e")
            }
        }
    }
}
