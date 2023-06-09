package com.wscauca.stropper.model.local

data class HistorialDb (
    var usuario:String,
    var puntuacion:Int,
)

data class HistorialDbConsulta (
    var id:Int,
    var usuario:String,
    var puntuacion:Int,
)