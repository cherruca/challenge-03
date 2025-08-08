package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.pokedex.R

class SpriteAdapter(private val sprites: List<String?>) : RecyclerView.Adapter<SpriteAdapter.SpriteViewHolder>() {

    class SpriteViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val spriteImage: ImageView = itemView.findViewById(R.id.main_sprite)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SpriteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_sprite, parent, false)
        return SpriteViewHolder(view)
    }

    override fun onBindViewHolder(holder: SpriteViewHolder, position: Int) {
        val spriteUrl = sprites[position]
        holder.spriteImage.load(spriteUrl) {
            crossfade(true)
            placeholder(R.drawable.rounded_downloading_24)
            error(R.drawable.rounded_error_24)
        }
    }

    override fun getItemCount() = sprites.size
}