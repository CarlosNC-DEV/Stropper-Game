package com.wscauca.stropper.ui.home

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout
import com.wscauca.stropper.R
import com.wscauca.stropper.databinding.ActivityHomeBinding
import com.wscauca.stropper.model.local.HistorialDb
import com.wscauca.stropper.model.local.db.ManagerHelperDb
import com.wscauca.stropper.ui.MainActivity
import com.wscauca.stropper.utilidades.preferencias.StropperApp.Companion.preferencias
import java.util.Random

class HomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityHomeBinding
    private lateinit var managerHelperDb: ManagerHelperDb

    private var color = mutableListOf("Amarillo", "Rojo", "Verde", "Azul", "Blanco", "Negro")
    private var textColor = mutableListOf(
        Color.YELLOW,
        Color.RED,
        Color.GREEN,
        Color.BLUE,
        Color.WHITE,
        Color.BLACK,
    )

    private var puntuacion : Int = 0
    private var random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        managerHelperDb = ManagerHelperDb(this)


        binding.txtPuntos.text = "Puntuación: ${puntuacion}"

        binding.btnVerdadero.setOnClickListener {
            verificarRespuesta(true)
        }

        binding.btnFalso.setOnClickListener {
            verificarRespuesta(false)
        }

        cargarColoresAndText()
        cargarTiempo()

    }

    private fun cargarTiempo() {
        object : CountDownTimer(preferencias.getDificultad().toLong(), 1000){
            override fun onTick(millisUntilFinished: Long) {
                binding.txtTiempo.text = "${ millisUntilFinished / 1000 } Segundos"
            }

            override fun onFinish() {
                val alert = Dialog(this@HomeActivity)

                alert.setContentView(R.layout.dialog_end_game)

                var puntacionFin = alert.findViewById<TextView>(R.id.txt_puntuacionEnd)

                var tlyUsuario = alert.findViewById<TextInputLayout>(R.id.tly_usuario)
                var edtUsuario = alert.findViewById<EditText>(R.id.edt_usuario)

                var newGame = alert.findViewById<Button>(R.id.btn_newGame)
                var saveGame = alert.findViewById<Button>(R.id.btn_saveGame)
                var irHome = alert.findViewById<Button>(R.id.btn_irHome)

                puntacionFin.text = "Puntuación Final: ${puntuacion}"

                newGame.setOnClickListener {
                    alert.dismiss()
                    puntuacion = 0
                    binding.txtPuntos.text = "Puntuación: ${puntuacion}"
                    cargarColoresAndText()
                    cargarTiempo()
                }

                saveGame.setOnClickListener {
                    if (edtUsuario.text.toString().isNotEmpty()){
                        val historialVal = HistorialDb(edtUsuario.text.toString(), puntuacion)
                        val resul = managerHelperDb.registrarHistorial(historialVal)
                        if (resul > 0){
                            startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                            alert.dismiss()
                            finish()
                        }
                    }else{
                        tlyUsuario.error = "Campo Requerido"
                    }
                }

                irHome.setOnClickListener {
                    startActivity(Intent(this@HomeActivity, MainActivity::class.java))
                    alert.dismiss()
                    finish()
                }

                alert.setCancelable(false)
                alert.show()

            }

        }.start()
    }


    private fun cargarColoresAndText() {
        var randomColor = random.nextInt(color.size)
        var coloresVal = color[randomColor]

        var randomTextColor = random.nextInt(textColor.size)
        var textColorVal = textColor[randomTextColor]

        // Setear los colores
        binding.txtColorAndText.text = coloresVal
        binding.txtColorAndText.setTextColor(textColorVal)

        var colorSelected = color.removeAt(randomColor)
        color.add(colorSelected)

    }

    private fun verificarRespuesta(respuesta: Boolean) {
        val colorVal = binding.txtColorAndText.text.toString()
        var textColorVal = binding.txtColorAndText.currentTextColor
        
        var isCorrect = (colorVal == getColorName(textColorVal))

        if(isCorrect && respuesta || !isCorrect && !respuesta){
            puntuacion++
            binding.txtPuntos.text = "Puntuación: ${puntuacion}"
        }

        cargarColoresAndText()

    }

    private fun getColorName(color: Int): String {
        return when(color){
            Color.YELLOW -> "Amarillo"
            Color.RED -> "Rojo"
            Color.GREEN -> "Verde"
            Color.BLUE -> "Azul"
            Color.WHITE -> "Blanco"
            Color.BLACK -> "Negro"
            else->""
        }
    }


}