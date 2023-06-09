package com.wscauca.stropper.utilidades.preferencias

import android.content.Context

class Preferencias(context: Context) {
    val storage = context.getSharedPreferences("StropperData", 0)

    fun saveDificultad(dificultad: Int){
        storage.edit().putInt("Dificultad", dificultad).apply()
    }

    fun getDificultad(): Int{
        return storage.getInt("Dificultad", 30000)
    }

    fun saveCambioIdioma(cambio: Boolean){
        storage.edit().putBoolean("Idioma", cambio).apply()
    }

    fun getCambioIdioma(): Boolean{
        return storage.getBoolean("Idioma", false)
    }

    fun saveCambioColor(cambio: Boolean){
        storage.edit().putBoolean("Color", cambio).apply()
    }

    fun getCambioColor(): Boolean{
        return storage.getBoolean("Color", false)
    }

}