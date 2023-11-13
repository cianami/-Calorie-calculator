package com.example.caloriecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.caloriecalculator.ui.theme.CalorieCalculatorTheme
import android.content.Context

class MainActivity : ComponentActivity() {
    var userCalorieData = UserCalorieData();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userCalorieData= convertFromJson(this);
        setContent {
            CalorieCalculatorTheme {
                MainDrawer(this, userCalorieData)
            }
        }
    }
}