package com.wscauca.stropper.utilidades.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.wscauca.stropper.R
import com.wscauca.stropper.model.local.HistorialDbConsulta

class HistorialAdapter(var historial:List<HistorialDbConsulta>) : RecyclerView.Adapter<HistorialHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistorialHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return HistorialHolder(layoutInflater.inflate(R.layout.items_historial, parent, false))
    }

    override fun getItemCount(): Int {
        return historial.size
    }

    override fun onBindViewHolder(holder: HistorialHolder, position: Int) {
        val items = historial[position]
        holder.render(items)
    }
}