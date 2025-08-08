package com.example.pokedex.ui.pokemondetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.api.PokeApi
import com.example.pokedex.data.PokemonDetailResponse
import kotlinx.coroutines.launch

class PokemonDetailViewModel : ViewModel() {
    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse?>()
    val pokemonDetail: MutableLiveData<PokemonDetailResponse?>
        get() = _pokemonDetail

    fun getPokemonDetail(name: String) {
        viewModelScope.launch {
            try {
                _pokemonDetail.value = PokeApi.retrofitService.getPokemonDetail(name)
            } catch (e: Exception) {
                // TODO: If _pokemonDetail, how are you handling that scenario in your UI?
                _pokemonDetail.value = null
                // TODO: are you planning to handle an error and loading state here?
                Log.e("ERROR", "could not retrieve data, $e")
            }
        }
    }
}
