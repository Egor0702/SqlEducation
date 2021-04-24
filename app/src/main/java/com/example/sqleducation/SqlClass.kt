package com.example.sqleducation

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class SqlClass (context : Context): SQLiteOpenHelper(context,  BASENAME, null, NUMBEVERSION){
    companion object{
        var BASENAME = "NIKS"
        var NUMBEVERSION = 1
    }

    override fun onCreate(db: SQLiteDatabase) {
        setSqlbase(db,0, NUMBEVERSION)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        setSqlbase(db,0, NUMBEVERSION)
    }
    fun setSqlbase(db:SQLiteDatabase, oldVersion: Int, newVersion: Int){
        if (oldVersion < 1){
         db.execSQL("CREATE TABLE NIKS( ID INTEGER PRIMARY KEY AUTOINCREMENT" +
                 ", NAME TEXT" +
                 ", EMAIL TEXT);")
        }
    }
    fun addData(db: SQLiteDatabase, name: String, email:String){
        val content = ContentValues()
        content.put("name", name)
        content.put("email", email)
        db.insert(BASENAME,null,content)
    }
}