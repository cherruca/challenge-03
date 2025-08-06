package com.example.pokedex.ui.pokedex

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.Pokemon

class PokedexAdapter : RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {
    private var dataSet = listOf<Pokemon>()

    class PokedexViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.item_name)
    } // todo: holder e inflate con data binding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokedexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false)
        // todo: databinding con inflate
        // todo: val view2 = ItemPokedexBinding.inflate(LayoutInflater.from(parent.context))
        return PokedexViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokedexViewHolder,
        position: Int
    ) {
        holder.textViewName.text = dataSet[position].name
        holder.textViewName.setOnClickListener {
            holder.textViewName.findNavController().navigate(
                PokedexFragmentDirections.actionPokedexFragmentToPokemonDetailFragment(dataSet[position].url)
            )
        }
    }

    override fun getItemCount(): Int = dataSet.size

    fun submitList(newData: List<Pokemon>) {
        dataSet = newData
        notifyDataSetChanged()
    }
}
