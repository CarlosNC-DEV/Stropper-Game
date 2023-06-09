package com.wscauca.stropper.model

class Constantes {
    companion object {
        val DB_NAME: String = "StropperDb"
        val DB_VERSION: Int = 1

        val TBL_HISTORIAL: String = "Historial"
        val ID_HISTORIAL: String = "id"
        val USARIO_HISTORIAL: String = "usuario"
        val PUNTUACION_HISTORIAL: String = "tiempo"


        val DB_CREATE_TBL_HISTORIAL: String = "CREATE TABLE $TBL_HISTORIAL ( " +
                "$ID_HISTORIAL INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$USARIO_HISTORIAL TEXT, " +
                "$PUNTUACION_HISTORIAL INTEGER )"

        val QUERY_HISTORIAL: String = "SELECT * FROM $TBL_HISTORIAL "
    }
}