package com.example.caloriecalculator.db

import DbHelper
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DbManager(context: Context?) {
    private val dbHelper: DbHelper
    private val database: SQLiteDatabase

    init {
        dbHelper = DbHelper(context)
        database = dbHelper.getWritableDatabase()
    }

    fun insertData(foodname: String?, kkal: Int, isProduct: Boolean) {
        val insertQuery = "INSERT INTO Foods (foodname, kkal, isProduct) VALUES ('$foodname', $kkal, ${if (isProduct) 1 else 0});"
        database.execSQL(insertQuery);
    }
    fun insertСonsumption(id_food: Int, gram: Int) {
        val insertQuery = "INSERT INTO Сonsumptions (id_food, gram,date) VALUES ($id_food, $gram,'${SimpleDateFormat("d MMMM, EEEE", Locale("ru", "RU")).format(Date())}');";
        database.execSQL(insertQuery);
    }

    fun getAllFoods(isProduct: Boolean): List<Food> {
        val foods = mutableListOf<Food>()
        val selectQuery = "SELECT * FROM Foods WHERE isProduct=${if (isProduct) 1 else 0} ORDER BY foodname"
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
    fun findFood(id: Int):Food{
        var food=Food();
        val selectQuery = "SELECT * FROM Foods WHERE id=$id"
        database.rawQuery(selectQuery, null).use { // .use requires API 16
            while (it.moveToNext()) {
                food.id = it.getInt(it.getColumnIndex("id").toInt())
                food.foodname = it.getString(it.getColumnIndex("foodname").toInt())
                food.kkal = it.getInt(it.getColumnIndex("kkal").toInt())
                food.isProduct=it.getInt(it.getColumnIndex("isProduct").toInt())!= 0
            }
        }
        return food
    }

    fun getAllTodayСonsumptions(): List<Сonsumption> {
        val consumptions = mutableListOf<Сonsumption>()
        val selectQuery = "SELECT * FROM Сonsumptions WHERE date='${SimpleDateFormat("d MMMM, EEEE", Locale("ru", "RU")).format(Date())}' ORDER BY id DESC"
        database.rawQuery(selectQuery, null).use { // .use requires API 16
            while (it.moveToNext()) {
                var consumption=Сonsumption()
                consumption.id = it.getInt(it.getColumnIndex("id").toInt())
                consumption.id_food = it.getInt(it.getColumnIndex("id_food").toInt())
                consumption.gram = it.getInt(it.getColumnIndex("gram").toInt())
                consumption.date=it.getString(it.getColumnIndex("date").toInt())
                consumptions.add(consumption)
            }
        }
        return consumptions
    }

    fun delete(id:Int){
        val deleteQuery="DELETE FROM Foods WHERE id=$id;"
        database.execSQL(deleteQuery);
        val deleteСonsumptionsQuery="DELETE FROM Сonsumptions WHERE id_food=$id;"
        database.execSQL(deleteСonsumptionsQuery);
    }
    fun deleteСonsumption(id:Int){
        val deleteQuery="DELETE FROM Сonsumptions WHERE id=$id;"
        database.execSQL(deleteQuery);
    }
}

class Food{
    public var id: Int=0;
    public var foodname: String="";
    public var kkal:Int=0;
    public var isProduct: Boolean=false;
}

class Сonsumption {
    public var id: Int = 0;
    public var id_food: Int=0;
    public var gram: Int = 0;
    public var date: String = "";
}


