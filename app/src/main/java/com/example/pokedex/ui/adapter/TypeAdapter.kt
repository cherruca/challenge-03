package com.example.pokedex.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pokedex.R
import com.example.pokedex.domain.model.Type

class TypeAdapter(private val types: List<Type>) : RecyclerView.Adapter<TypeAdapter.TypeViewHolder>() {

    class TypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val typeName: TextView = itemView.findViewById(R.id.txt_type_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypeViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_type, parent, false)
        return TypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: TypeViewHolder, position: Int) {
        val type = types[position]
        holder.typeName.text = type.type.name.replaceFirstChar { it.uppercase() }
    }

    override fun getItemCount() = types.size
}