package com.example.caloriecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.caloriecalculator.ui.theme.CalorieCalculatorTheme
import androidx.navigation.compose.*
import com.example.caloriecalculator.db.DbManager
import com.example.caloriecalculator.ui.theme.AddProduct

class MainActivity : ComponentActivity() {
    private val currentInstance
        get() = this

    var userCalorieData = UserCalorieData();
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userCalorieData = convertFromJson(this);

        var dbmanager = DbManager(this);
        dbmanager.insertData("rfff", 0);

        setContent {
            CalorieCalculatorTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.Home.route
                ) {
                    composable(Routes.Home.route) {
                        MainDrawer(currentInstance, userCalorieData, navController)
                    }
                    composable(Routes.Product.route) {
                        AddProduct { navController.navigate(Routes.Home.route) }
                    }
                }
            }
        }
    }
}

enum class Routes(val route: String) {
    Home("home"),
    Product("product"),
}