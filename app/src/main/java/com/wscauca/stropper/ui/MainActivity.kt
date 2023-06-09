package com.wscauca.stropper.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.wscauca.stropper.R
import com.wscauca.stropper.databinding.ActivityMainBinding
import com.wscauca.stropper.ui.config.ConfigActivity
import com.wscauca.stropper.ui.home.HomeActivity
import com.wscauca.stropper.ui.misGames.GamesActivity

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.irConfig.setOnClickListener {
            startActivity(Intent(this, ConfigActivity::class.java))
        }

        binding.irMisJuegos.setOnClickListener {
            startActivity(Intent(this, GamesActivity::class.java))
        }

        binding.irNuevoJuego.setOnClickListener {
            startActivity(Intent(this, HomeActivity::class.java))
        }

    }
}