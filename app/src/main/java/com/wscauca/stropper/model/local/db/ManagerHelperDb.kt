package com.wscauca.stropper.model.local.db

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import com.wscauca.stropper.model.Constantes
import com.wscauca.stropper.model.local.HistorialDb
import com.wscauca.stropper.model.local.HistorialDbConsulta

class ManagerHelperDb(context: Context) {

    private var helperDb: HelperDb
    var db : SQLiteDatabase ?= null

    init {
        helperDb = HelperDb(context)
    }

    fun openDbWr(){
        db = helperDb.writableDatabase
    }

    fun openDbRd(){
        db = helperDb.readableDatabase
    }

    fun closeDb(){
        if(db != null){
            db?.close()
        }
    }

    fun registrarHistorial(historialDb: HistorialDb) : Long{
        openDbWr()
        val values = ContentValues()
        values.put(Constantes.USARIO_HISTORIAL, historialDb.usuario)
        values.put(Constantes.PUNTUACION_HISTORIAL, historialDb.puntuacion)
        val resul = db?.insert(Constantes.TBL_HISTORIAL, null, values)
        return resul!!
    }

    fun historialFound(): ArrayList<HistorialDbConsulta>{
        openDbRd()
        val historialVal : ArrayList<HistorialDbConsulta> = ArrayList()
        val resul = db?.rawQuery(Constantes.QUERY_HISTORIAL, null)
        if(resul!!.moveToFirst()){
            do {
                val historial = HistorialDbConsulta(
                    resul.getInt(0),
                    resul.getString(1),
                    resul.getInt(2)
                )
                historialVal.add(historial)
            }while (resul.moveToNext())
        }
        return historialVal
    }


}