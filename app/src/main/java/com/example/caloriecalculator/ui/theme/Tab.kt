package com.example.caloriecalculator.ui.theme

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.caloriecalculator.Dishes
import com.example.caloriecalculator.Products

typealias ComposableFun = @Composable () -> Unit
class Tab (var title: String, var screen: ComposableFun)