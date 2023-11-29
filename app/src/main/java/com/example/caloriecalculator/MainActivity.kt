package com.example.caloriecalculator

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.caloriecalculator.ui.theme.CalorieCalculatorTheme
import androidx.navigation.compose.*
import com.example.caloriecalculator.db.DbManager
import com.example.caloriecalculator.ui.theme.AddDish
import com.example.caloriecalculator.ui.theme.AddProduct
import com.google.accompanist.pager.ExperimentalPagerApi

class MainActivity : ComponentActivity() {
    private val currentInstance
        get() = this

    var userCalorieData = UserCalorieData();
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userCalorieData = convertFromJson(this);

        var dbmanager = DbManager(this);

        setContent {
            CalorieCalculatorTheme {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = Routes.Home.route
                ) {
                    composable(Routes.Home.route) {
                        MainDrawer(currentInstance, userCalorieData, navController, dbmanager)
                    }
                    composable(Routes.Product.route) {
                        AddProduct(dbmanager) { navController.navigate(Routes.Home.route)}
                    }
                    composable(Routes.Dish.route){
                        AddDish (dbmanager){ navController.navigate(Routes.Home.route)}
                    }
                }
            }
        }
    }
}

enum class Routes(val route: String) {
    Home("home"),
    Product("product"),
    Dish("dish")
}