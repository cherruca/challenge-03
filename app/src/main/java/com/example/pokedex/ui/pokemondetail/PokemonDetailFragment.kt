package com.example.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import coil.load
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding

    private val viewModel: PokemonDetailViewModel by lazy {
        ViewModelProvider(this)[PokemonDetailViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val args = PokemonDetailFragmentArgs.fromBundle(requireArguments())
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)

        viewModel.getPokemonDetail(name = args.pokemonId)
        viewModel.pokemonDetail.observe(viewLifecycleOwner) { response ->
            binding.detailTitle.text = response?.name
            binding.mainSprite.load(response?.sprites?.frontDefault) {
                crossfade(true)
                placeholder(R.drawable.rounded_downloading_24)
                error(R.drawable.rounded_error_24)
            }
            // todo clean code
            binding.detailHeightWeight.text =
                buildString {
                    append("%.2f".format(response?.height?.times(0.1)))
                    append(" mts - ")
                    append("%.2f".format(response?.weight?.times(0.1)))
                    append(" kg ")
                }
            binding.detailExtra.text =
                buildString {
                    response?.stats?.forEach { stat ->
                        append(stat.stat.name + ": ")
                        append(stat.baseStat)
                        append("\n")
                    }
                }
        }
        return binding.root
    }
}
