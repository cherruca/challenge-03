package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import coil.load
import com.example.pokedex.R
import com.example.pokedex.domain.model.ListItem
import com.example.pokedex.ui.pokedex.PokedexFragmentDirections
import com.google.android.material.card.MaterialCardView

class FavoriteViewHolder private constructor(
    itemView: View
) : BaseViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.item_name)
    private val cardView: MaterialCardView = itemView.findViewById(R.id.pokecard)

    private val imageView: ImageView = itemView.findViewById(R.id.item_sprite)

    override fun bind(item: ListItem) {
        require(item is ListItem.PokemonItem)
        val pokemon = item.pokemonUI
        textView.text = pokemon.name
        imageView.load(pokemon.imageShiny) {
            crossfade(true)
            placeholder(R.drawable.rounded_downloading_24)
            error(R.drawable.rounded_error_24)
        }
        cardView.setOnClickListener {
            it.findNavController().navigate(
                PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(pokemon.name)
            )
        }
    }

    companion object {
        fun create(parent: ViewGroup) = FavoriteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_favorite_pokedex, parent, false)
        )
    }
}