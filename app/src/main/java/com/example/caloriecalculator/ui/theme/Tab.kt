package com.example.caloriecalculator.ui.theme

import androidx.compose.runtime.Composable
import com.example.caloriecalculator.Dishes
import com.example.caloriecalculator.Products

typealias ComposableFun = @Composable () -> Unit
sealed class Tab (var title: String, var screen: ComposableFun) {
    object ScreenProducts : Tab("Продукты", {Products()})
    object ScreenDishes : Tab("Блюда", { Dishes() })
}