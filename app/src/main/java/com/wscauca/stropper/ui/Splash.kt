package com.wscauca.stropper.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import androidx.appcompat.app.AppCompatDelegate
import com.wscauca.stropper.R
import com.wscauca.stropper.databinding.ActivitySplashBinding
import com.wscauca.stropper.ui.config.ConfigActivity
import com.wscauca.stropper.utilidades.preferencias.StropperApp.Companion.preferencias

class Splash : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding
    val configActivity = ConfigActivity()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val modVista = preferencias.getCambioColor()
        when(modVista){
            true -> {
                modoNoche()
            }
            false -> {
                modoDefault()
            }
        }


        object : CountDownTimer(1200, 1000){
            override fun onTick(millisUntilFinished: Long) {

            }

            override fun onFinish() {
                startActivity(Intent(this@Splash, MainActivity::class.java))
                finish()
            }

        }.start()

    }
    fun modoDefault() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        delegate.applyDayNight()
    }

    fun modoNoche() {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        delegate.applyDayNight()
    }
}