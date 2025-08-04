package com.example.pokedex.ui.pokedex

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import com.example.pokedex.R
import com.example.pokedex.databinding.FragmentPokedexBinding

class PokedexFragment : Fragment() {
    companion object {
        fun newInstance() = PokedexFragment()
    }

    private lateinit var binding: FragmentPokedexBinding

    private val viewModel: PokedexViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokedexBinding.inflate(inflater, container, false)
        binding.btnDetail.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_pokedexFragment_to_pokemonDetailFragment)
        }
        return binding.root
    }
}
