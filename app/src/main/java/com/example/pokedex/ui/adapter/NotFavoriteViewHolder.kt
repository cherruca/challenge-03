package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.pokedex.R
import com.example.pokedex.domain.model.ListItem
import com.example.pokedex.ui.pokedex.PokedexFragmentDirections
import com.google.android.material.card.MaterialCardView

class NotFavoriteViewHolder private constructor(
    itemView: View
) : BaseViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.item_name)
    private val cardView: MaterialCardView = itemView.findViewById(R.id.pokecard)

    override fun bind(item: ListItem) {
        require(item is ListItem.PokemonItem)
        val pokemon = item.pokemonUI
        textView.text = pokemon.name
        cardView.setOnClickListener {
            it.findNavController().navigate(
                PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(pokemon.name)
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup) = NotFavoriteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_pokedex, parent, false)
        )
    }
}