package com.wscauca.stropper.utilidades.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.wscauca.stropper.databinding.ItemsHistorialBinding
import com.wscauca.stropper.model.local.HistorialDbConsulta

class HistorialHolder(view: View) : ViewHolder(view) {

    val binding = ItemsHistorialBinding.bind(view)

    fun render(historialDbConsulta: HistorialDbConsulta){
        binding.txtUsuarioHis.text = "Usuario: ${historialDbConsulta.usuario}"
        binding.txtPuntosHis.text = "Puntuación Final: ${historialDbConsulta.puntuacion}"
    }

}