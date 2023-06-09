package com.wscauca.stropper.model.local.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.wscauca.stropper.model.Constantes

class HelperDb(context: Context) : SQLiteOpenHelper(context, Constantes.DB_NAME, null, Constantes.DB_VERSION) {
    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL(Constantes.DB_CREATE_TBL_HISTORIAL)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}