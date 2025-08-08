package com.example.pokedex.ui.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.domain.model.ListItem

abstract class BaseViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {
    abstract fun bind(item: ListItem)
}