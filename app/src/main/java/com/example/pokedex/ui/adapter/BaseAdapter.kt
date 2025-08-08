package com.example.pokedex.ui.adapter

import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.domain.model.ListItem

abstract class BasePokemonAdapter : RecyclerView.Adapter<BaseViewHolder>() {
    final override fun getItemViewType(position: Int): Int {
        return getItem(position).viewType.id
    }

    abstract fun getItem(position: Int): ListItem
}