package com.example.pokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.domain.model.PokemonUI
import com.example.pokedex.databinding.FragmentPokedexBinding
import com.example.pokedex.domain.model.ListItem
import com.example.pokedex.ui.adapter.PokemonListAdapter

class PokedexFragment : Fragment() {
    private lateinit var binding: FragmentPokedexBinding

    private val viewModel: PokedexViewModel by lazy {
        ViewModelProvider(this)[PokedexViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokedexBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        return binding.root
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        binding.recyclerviewPokedex.layoutManager = layoutManager
        viewModel.pokemons.observe(viewLifecycleOwner) { response ->
            val dataset = response?.results ?: emptyList()
            val pokemonsUI =
                dataset.map { pokemon ->
                    PokemonUI(
                        name = pokemon.name,
                        url = pokemon.url,
                        isFavorite = viewModel.favoriteRepository.isFavorite(pokemon.name),
                        imageDefault = "",
                        imageShiny = ""
                    )
                }
            val items = pokemonsUI.map { pokemonUI ->
                ListItem.PokemonItem(
                    pokemonUI = pokemonUI,
                    viewType = if (pokemonUI.isFavorite)
                        ListItem.ViewType.POKEMON_FAVORITE
                    else
                        ListItem.ViewType.POKEMON_NOT_FAVORITE
                )
            }
            binding.recyclerviewPokedex.adapter = PokemonListAdapter(items)
        }
    }
}
