package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.model.PokemonUI
import com.example.pokedex.data.list.ListItem
import com.example.pokedex.ui.pokedex.PokedexFragmentDirections
import com.google.android.material.card.MaterialCardView

// TODO: adapters should be split into separate files
class PokemonAdapter(
    private val items: List<ListItem>
) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun getItemViewType(position: Int): Int = items[position].getListItemType()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        when (viewType) {
            // TODO: this hardcoded numbers are a bad practice.
            //  We cannot easily tell what 1 and 2 means, that's a bad code.
            1 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_favorite_pokedex, parent, false)
                return ViewHolderFavorite(view)
            }

            2 -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pokedex, parent, false)
                return ViewHolderNotFavorite(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_pokedex, parent, false)
                return ViewHolderNotFavorite(view)
            }
        }
    }

    // TODO: Please, remove all the unnecessary empty-lines
    override fun onBindViewHolder(
        holder: BaseViewHolder,
        position: Int
    ) {
        holder.bind(items[position])

    }

    override fun getItemCount(): Int = items.size
}

abstract class BaseViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: ListItem)
}

class ViewHolderFavorite(
    itemView: View
) : BaseViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.item_name)
    private val cardView: MaterialCardView = itemView.findViewById(R.id.pokecard)

    override fun bind(item: ListItem) {
        // TODO: `itemA` is a bad naming, try to find a more meaningful one
        val itemA = item as PokemonUI
        textView.text = itemA.name
        cardView.setOnClickListener {
            cardView.findNavController().navigate(
                PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(itemA.name)
            )
        }
    }
}

class ViewHolderNotFavorite(
    itemView: View
) : BaseViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.item_name)
    private val cardView: MaterialCardView = itemView.findViewById(R.id.pokecard)

    override fun bind(item: ListItem) {
        // TODO: same here, `itemB` is a bad naming
        val itemB = item as PokemonUI
        textView.text = itemB.name
        cardView.setOnClickListener {
            cardView.findNavController().navigate(
                PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(itemB.name)
            )
        }
    }
}
