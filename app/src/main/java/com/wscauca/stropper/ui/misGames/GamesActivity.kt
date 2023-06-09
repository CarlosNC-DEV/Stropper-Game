package com.wscauca.stropper.ui.misGames

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.wscauca.stropper.R
import com.wscauca.stropper.databinding.ActivityGamesBinding
import com.wscauca.stropper.model.local.db.ManagerHelperDb
import com.wscauca.stropper.utilidades.adapters.HistorialAdapter

class GamesActivity : AppCompatActivity() {

    private lateinit var binding: ActivityGamesBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGamesBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerHelperDb = ManagerHelperDb(this)

        val resul = managerHelperDb.historialFound()
        val rcvHistorial = binding.rcvhistorial
        rcvHistorial.layoutManager = LinearLayoutManager(this)
        rcvHistorial.adapter = HistorialAdapter(resul)


    }
}