package com.example.pokedex.ui.pokedex

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.data.Pokemon
import com.example.pokedex.databinding.FragmentPokedexBinding

class PokedexFragment : Fragment() {
    private lateinit var binding: FragmentPokedexBinding
    private val pokemonIndex: Int = 2
    private lateinit var recyclerView: RecyclerView
    private lateinit var dataset: List<Pokemon>
    private lateinit var customAdapter: PokedexAdapter
    private lateinit var layoutManager: GridLayoutManager

    private val viewModel: PokedexViewModel by lazy {
        ViewModelProvider(this).get(PokedexViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPokedexBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.btnDetail.setOnClickListener { view: View ->
            view.findNavController().navigate(PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(pokemonIndex))
        }

        return binding.root
    }

    private fun displayPokedexRecyclerView() {
        layoutManager = GridLayoutManager(context, 2)
        customAdapter = PokedexAdapter()

        viewModel.pokemons.observe(viewLifecycleOwner) {
            dataset = viewModel.pokemons.value!!.results!!
            customAdapter =
                PokedexAdapter().apply {
                    submitList(dataset)
                    // notifyDataSetChanged()
                }
            // customAdapter.submitList(dataset)
            // customAdapter.notifyDataSetChanged()
            Log.d("FRAGMENT", dataset.toString())
        }
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        recyclerView = binding.recyclerviewPokedex
        // customAdapter = PokedexAdapter()
        layoutManager = GridLayoutManager(context, 2)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = PokedexAdapter()

        viewModel.pokemons.observe(viewLifecycleOwner) { response ->
            val dataset = response?.results ?: emptyList()
            // dataset = viewModel.pokemons.value!!.results!!
            recyclerView.adapter =
                PokedexAdapter().apply {
                    submitList(dataset)
                    // notifyDataSetChanged()
                }
            Log.d("FRAGMENT", dataset.toString())
        }
    }
}
