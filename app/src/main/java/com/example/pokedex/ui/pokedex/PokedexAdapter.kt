package com.example.pokedex.ui.pokedex

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.data.Pokemon

class PokedexAdapter : RecyclerView.Adapter<PokedexAdapter.PokedexViewHolder>() {
    private var dataSet = listOf<Pokemon>()

    class PokedexViewHolder(
        view: View
    ) : RecyclerView.ViewHolder(view) {
        val textViewName: TextView = view.findViewById(R.id.item_name)
    } // holder e inflate con data binding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PokedexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pokedex, parent, false)
        // databinding con inflate
//        val view2 = ItemPokedexBinding.inflate(LayoutInflater.from(parent.context))
        Log.d("VIEW", view.id.toString())
        return PokedexViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: PokedexViewHolder,
        position: Int
    ) {
        holder.textViewName.text = dataSet[position].name
        Log.d("BINDVIEWHOLDER", dataSet[position].name)
    }

    override fun getItemCount(): Int {
        Log.d("SIZE", dataSet.size.toString())
        return dataSet.size
    }

    fun submitList(newData: List<Pokemon>) {
        dataSet = newData
        notifyDataSetChanged()
        Log.d("ADAPTER", dataSet.toString())
    }
}
