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

    fun insertData(foodname: String?, kkal: Int, isProduct: Boolean) {
        val insertQuery = "INSERT INTO MyTable (foodname, kkal, isProduct) VALUES ('$foodname', $kkal, ${if (isProduct) 1 else 0});"
        database.execSQL(insertQuery);
    }

    fun getAllFoods(isProduct: Boolean): List<Food> {
        val foods = mutableListOf<Food>()
        val selectQuery = "SELECT * FROM MyTable WHERE isProduct=${if (isProduct) 1 else 0}"
        database.rawQuery(selectQuery, null).use { // .use requires API 16
            while (it.moveToNext()) {
                var food=Food()
                food.id = it.getInt(it.getColumnIndex("id").toInt())
                food.foodname = it.getString(it.getColumnIndex("foodname").toInt())
                food.kkal = it.getInt(it.getColumnIndex("kkal").toInt())
                food.isProduct=isProduct
                foods.add(food)
            }
        }
        return foods
    }
}

class Food{
    public var id: Int=0;
    public var foodname: String="";
    public var kkal:Int=0;
    public var isProduct: Boolean=false;
}


