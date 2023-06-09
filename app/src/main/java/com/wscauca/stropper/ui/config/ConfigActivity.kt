package com.wscauca.stropper.ui.config

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import com.wscauca.stropper.R
import com.wscauca.stropper.databinding.ActivityConfigBinding
import com.wscauca.stropper.utilidades.preferencias.StropperApp.Companion.preferencias
import java.util.Locale

class ConfigActivity : AppCompatActivity() {

    private lateinit var binding: ActivityConfigBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConfigBinding.inflate(layoutInflater)
        setContentView(binding.root)


        cargarDificultad()
        cargarModoVista()

        // Modo vista
        binding.swtModoVista.setOnCheckedChangeListener{_, isSelected->
            if(isSelected){
                modoNoche()
                preferencias.saveCambioColor(true)
            }else{
                modoDefault()
                preferencias.saveCambioColor(false)
            }
        }

        // Idioma
        binding.swtModoIdioma.setOnCheckedChangeListener{_, isSelected->
            if(isSelected){
                cambiarIdoma("en")
                preferencias.saveCambioIdioma(true)
            }else{
                cambiarIdoma("es")
                preferencias.saveCambioIdioma(false)
            }
        }

        // Dificultad
        binding.checkFacil.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                preferencias.saveDificultad(30000)
                cargarDificultad()
            }
        }
        binding.checkMedio.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                preferencias.saveDificultad(20000)
                cargarDificultad()
            }
        }
        binding.checkDificil.setOnCheckedChangeListener { _, isChecked ->
            if(isChecked){
                preferencias.saveDificultad(10000)
                cargarDificultad()
            }
        }
    }

    private fun cargarModoVista() {
        val modVista = preferencias.getCambioColor()
        when(modVista){
            true -> {
                binding.swtModoVista.isChecked = true
            }
            false -> {
                binding.swtModoVista.isChecked = false
            }
        }
    }

    private fun cargarDificultad() {
        val dificultad = preferencias.getDificultad()
        when(dificultad){
            30000 -> {
                binding.checkFacil.isChecked = true
                binding.checkMedio.isChecked = false
                binding.checkDificil.isChecked = false
            }
            20000 -> {
                binding.checkFacil.isChecked = false
                binding.checkMedio.isChecked = true
                binding.checkDificil.isChecked = false
            }
            10000 -> {
                binding.checkFacil.isChecked = false
                binding.checkMedio.isChecked = false
                binding.checkDificil.isChecked = true
            }
        }
    }

    private fun cambiarIdoma(idioma: String) {
        val recursos = resources
        val diplayMetrics = recursos.displayMetrics
        val configuracion = recursos.configuration

        val localizacion = Locale(idioma)
        Locale.setDefault(localizacion)

        configuracion.setLocale(localizacion)
        resources.updateConfiguration(configuracion, diplayMetrics)
    }

    fun modoDefault() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    fun modoNoche() {
        AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
}