package com.example.pokedex.ui.pokemondetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.pokedex.databinding.FragmentPokemonDetailBinding

class PokemonDetailFragment : Fragment() {
    private lateinit var binding: FragmentPokemonDetailBinding
    private val viewModel: PokemonDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokemonDetailBinding.inflate(inflater, container, false)
        val args = PokemonDetailFragmentArgs.fromBundle(requireArguments())
        Toast.makeText(requireContext(), "Pokemon with index of: ${args.pokemonId}", Toast.LENGTH_LONG).show()
        return binding.root
    }
}
