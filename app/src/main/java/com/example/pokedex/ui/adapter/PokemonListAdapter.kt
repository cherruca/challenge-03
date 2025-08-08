package com.example.pokedex.ui.adapter

import android.view.ViewGroup
import com.example.pokedex.domain.model.ListItem

class PokemonListAdapter(
    private val items: List<ListItem>
) : BasePokemonAdapter() {

    override fun getItem(position: Int) = items[position]

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        when (ListItem.ViewType.fromId(viewType)) {
            ListItem.ViewType.POKEMON_FAVORITE -> FavoriteViewHolder.create(parent)
            ListItem.ViewType.POKEMON_NOT_FAVORITE -> NotFavoriteViewHolder.create(parent)
        }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount() = items.size
}