package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.PokemonUI
import com.example.pokedex.data.list.ListItem

class PokemonAdapter(
    private val items: List<ListItem>
) : RecyclerView.Adapter<BaseViewHolder>() {
    override fun getItemViewType(position: Int): Int = items[position].getListItemType()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        return when (viewType) {
            1 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorite_pokedex, parent, false)
                return ViewHolderFavorite(view)
            }
            2 -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false)
                return ViewHolderNotFavorite(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false)
                return ViewHolderNotFavorite(view)
            }
        }
    }

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

    override fun bind(item: ListItem) {
        val itemA = item as PokemonUI
        textView.text = itemA.name
    }
}

class ViewHolderNotFavorite(
    itemView: View
) : BaseViewHolder(itemView) {
    private val textView: TextView = itemView.findViewById(R.id.item_name)

    override fun bind(item: ListItem) {
        val itemB = item as PokemonUI
        textView.text = itemB.name
    }
}
