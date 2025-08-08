package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.model.Stat

class StatAdapter(private val stats: List<Stat>) : RecyclerView.Adapter<StatAdapter.StatViewHolder>() {

    class StatViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val statName: TextView = itemView.findViewById(R.id.txt_stat_name)
        val statValue: TextView = itemView.findViewById(R.id.txt_stat_value)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_stat, parent, false)
        return StatViewHolder(view)
    }

    override fun onBindViewHolder(holder: StatViewHolder, position: Int) {
        val stat = stats[position]
        holder.statName.text = stat.stat.name.replaceFirstChar { it.uppercase() }
        holder.statValue.text = stat.baseStat.toString()
    }

    override fun getItemCount() = stats.size
}