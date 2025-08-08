package com.example.pokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pokedex.data.PokemonUI
import com.example.pokedex.databinding.FragmentPokedexBinding
import com.example.pokedex.ui.adapter.PokemonAdapter

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
            // todo clean code
            val pokemonsUI =
                dataset.map { pokemon ->
                    PokemonUI(
                        name = pokemon.name,
                        url = pokemon.url,
                        isFavorite = viewModel.favoriteRepository.isFavorite(pokemon.name),
                        image = ""
                    )
                }
            binding.recyclerviewPokedex.adapter = PokemonAdapter(pokemonsUI)
        }
    }
}
