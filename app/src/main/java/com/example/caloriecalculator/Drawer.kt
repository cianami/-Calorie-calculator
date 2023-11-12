package com.example.caloriecalculator
import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import android.content.Context

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainDrawer(context: Context,){
    var screenState by remember { mutableStateOf(Screen.Home) }
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color(0xffF2F6F7)
    ) {
        Scaffold (
            bottomBar = { BottomBar { screen -> screenState = screen } },
            modifier = Modifier.fillMaxSize()
        ) {
            ScreenContents(context, screenState)
        }
    }
}
@Composable
fun ScreenContents(context: Context,selectedScreen: Screen, modifier:Modifier = Modifier){
    when (selectedScreen){
        Screen.Home -> HomeScreen(modifier)
        Screen.Calculator -> Calculator(context,modifier)
        Screen.Fridge -> Fridge(modifier)
    }
}

public enum class Screen(val text: String){
    Home("Главная"),
    Calculator("Калькулятор калорий"),
    Fridge("Холодильник")
}