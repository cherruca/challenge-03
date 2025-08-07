package com.example.pokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.PokemonUI
import com.google.android.material.card.MaterialCardView

class PokedexAdapter : RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {
    private var dataSet = listOf<PokemonUI>()

    class PokedexViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.item_name)
        val favoriteViewName: TextView = view.findViewById(R.id.item_favorite)
        val cardView: MaterialCardView = view.findViewById(R.id.pokecard)
    } // todo: holder e inflate con data binding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokedexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false)
        // todo: databinding con inflate: val view2 = ItemPokedexBinding.inflate(LayoutInflater.from(parent.context))
        return PokedexViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokedexViewHolder,
        position: Int
    ) {
        holder.textViewName.text = dataSet[position].name
        // todo apply different viewtype when favorite = true
        holder.favoriteViewName.text = if (dataSet[position].isFavorite) "favorite" else ""
        holder.cardView.setOnClickListener {
            holder.cardView.findNavController().navigate(
                PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(dataSet[position].name)
            )
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun submitList(newData: List<PokemonUI>) {
        dataSet = newData
        notifyDataSetChanged()
    }
}
