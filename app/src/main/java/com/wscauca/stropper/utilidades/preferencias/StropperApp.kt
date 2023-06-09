package com.wscauca.stropper.utilidades.preferencias

import android.app.Application

class StropperApp : Application() {
    companion object{
        lateinit var preferencias: Preferencias
    }

    override fun onCreate() {
        super.onCreate()
        preferencias = Preferencias(applicationContext)
    }
}