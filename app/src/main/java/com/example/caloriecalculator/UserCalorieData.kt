package com.example.caloriecalculator
import android.content.Context
import com.google.gson.Gson
import java.io.File
import java.io.IOException


data class UserCalorieData(
    var age: String="25",
    var gender: String="Мужчина",
    var weight: String="80",
    var height: String="175",
    var result: Int=0
)
fun convertToJson(context: Context,userCalorieData: UserCalorieData) {
    writeJSONToFile(context,"UserCalorieData.json",Gson().toJson(userCalorieData))
}
fun writeJSONToFile(context: Context, fileName: String, jsonString: String) {
    try {
        context.openFileOutput(fileName, Context.MODE_PRIVATE).use {
            it.write(jsonString.toByteArray())
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

fun convertFromJson(context: Context): UserCalorieData {
    try {
        val jsonString =
            File("/data/data/com.example.caloriecalculator/files/UserCalorieData.json").readText()
        return Gson().fromJson(jsonString, UserCalorieData::class.java)
    }
    catch (e: IOException) {
       return UserCalorieData()
    }
}

fun a(context: Context, userCalorieData: UserCalorieData, age: String,gender: String, weight: String,height: String):Int{
    userCalorieData.age=age;
    userCalorieData.gender=gender;
    userCalorieData.weight=weight;
    userCalorieData.height=height;
    var iAge = ("0${userCalorieData.age}").toInt()
    var iWeight = ("0$weight").toInt()
    var iHeight = ("0$height").toInt()
    userCalorieData.result= (10*iWeight+6.25*iHeight-5*iAge).toInt();
    if(gender==Gender.female) userCalorieData.result-=161;
    else userCalorieData.result+=5;
    userCalorieData.result=kotlin.math.max(userCalorieData.result,5);
    convertToJson(context,userCalorieData)
    return userCalorieData.result;
}