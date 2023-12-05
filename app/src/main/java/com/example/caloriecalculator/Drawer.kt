package com.example.caloriecalculator

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.caloriecalculator.db.DbManager
import com.example.caloriecalculator.ui.theme.AddDailyProduct

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(context: Context, userCalorieData: UserCalorieData, navController: NavController, dbManager: DbManager) {
    var screenState by remember { mutableStateOf(currentState) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xffF2F6F7)
    ) {
        Scaffold(
            bottomBar = { BottomBar { screen ->
                screenState = screen
                currentState = screen
            } },
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenContents(context, screenState, userCalorieData, navController, dbManager)
        }
    }
}

@Composable
fun ScreenContents(
    context: Context,
    selectedScreen: Screen,
    userCalorieData: UserCalorieData,
    navController: NavController,
    dbManager: DbManager,
    modifier: Modifier = Modifier
) {
    when (selectedScreen) {
        Screen.Home -> HomeScreen(userCalorieData, modifier, navController)
        Screen.Calculator -> Calculator(context, userCalorieData, modifier)
        Screen.Fridge -> Fridge(modifier, navController, dbManager)
        Screen.DailyProduct -> AddDailyProduct { navController.navigate(Routes.Home.route) }
    }
}

var currentState = Screen.Home
public enum class Screen(val text: String) {
    Home("Главная"),
    Calculator("Калькулятор калорий"),
    Fridge("Холодильник"),
    DailyProduct("Продукт дня")
}