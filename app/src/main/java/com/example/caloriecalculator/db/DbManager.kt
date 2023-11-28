package com.example.caloriecalculator.db

import DbHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase


class DbManager(context: Context?) {
    private val dbHelper: DbHelper
    private val database: SQLiteDatabase

    init {
        dbHelper = DbHelper(context)
        database = dbHelper.getWritableDatabase()
    }

    fun insertData(namefood: String?, kkal: Int) {
        val insertQuery = "INSERT INTO MyTable (foodname, kkal) VALUES ('Тортик', 500);"
        database.execSQL(insertQuery);
    }

}


