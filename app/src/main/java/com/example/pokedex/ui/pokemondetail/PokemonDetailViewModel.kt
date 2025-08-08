package com.example.pokedex.ui.pokemondetail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.network.PokeApi
import com.example.pokedex.domain.model.PokemonDetailResponse
import com.example.pokedex.domain.repository.FavoriteRepositoryImpl
import kotlinx.coroutines.launch

class PokemonDetailViewModel : ViewModel() {
    private val _pokemonDetail = MutableLiveData<PokemonDetailResponse?>()
    val pokemonDetail: MutableLiveData<PokemonDetailResponse?>
        get() = _pokemonDetail
    val favoriteRepository = FavoriteRepositoryImpl()

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

    fun toggleFavorite(name: String) {
        viewModelScope.launch {
            favoriteRepository.toggleFavorite(name)
            Log.d("VMNAME",name)
        }
    }
}
