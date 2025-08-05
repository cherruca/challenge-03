package com.example.pokedex.ui.pokedex

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.PokeApi
import com.example.pokedex.data.Pokemon
import kotlinx.coroutines.launch

class PokedexViewModel : ViewModel() {
    private val _pokemons = MutableLiveData<List<Pokemon>>()
    val pokemons: LiveData<List<Pokemon>>
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
                _pokemons.value = ArrayList()
                Log.d("ERROR", "could not retrieve from retrofit $e")
            }
        }
    }
}
