package com.example.caloriecalculator.ui.theme

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ExposedDropdownMenuBox
import androidx.compose.material.Text
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import com.example.caloriecalculator.Gender
import com.example.caloriecalculator.ResultText
import com.example.caloriecalculator.UserCalorieData
import com.example.caloriecalculator.a
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.lazy.LazyColumn

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class)
@Composable
fun AddDailyProduct(goBackCallback: () -> Unit) {
    val selectType = remember { mutableStateOf(Type.product) }
    Column(
        modifier =
        Modifier
            .fillMaxSize()
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
        horizontalAlignment = Alignment.Start
    ) {
        androidx.compose.material3.Text(
            text = "Добавь продукт в свой ежедневный рацион",
            color = Color(0xff3A6279),
            fontSize = 22.sp,
            textAlign = TextAlign.Center,
            //modifier = Modifier.offset(y = -200.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y=20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.offset()
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    RadioButton(
                        selected = selectType.value == Type.product,
                        onClick = { selectType.value = Type.product },

                        )
                    androidx.compose.material3.Text(Type.product , fontSize = 20.sp, color = Color(0xff473366))

                    RadioButton(
                        selected = selectType.value == Type.dish,
                        onClick = { selectType.value = Type.dish }
                    )
                    androidx.compose.material3.Text(Type.dish, fontSize = 20.sp, color = Color(0xff473366))
                }
            }
        }
        Demo_ExposedDropdownMenuBox()
        Column(modifier = Modifier.offset()) {
            androidx.compose.material3.Text(
                text = "Введите количество грамм",
                color = Color(0xff473366),
                fontSize = 20.sp,
                modifier = Modifier.offset(y = 20.dp)
            )

            TextField(
                modifier = Modifier.width(100.dp).offset(y=25.dp),
                value = "",
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = Color(0xffEFF2FF)
                ),
                placeholder = { androidx.compose.material3.Text("0") },
                singleLine = true,
                onValueChange = { newText ->
                    if (newText.toIntOrNull() != null || newText == "") {

                    }
                }
            )
        }
        ResultKalorie(5)
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter
        )
        {
            Button(
                onClick = {
                    goBackCallback()
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFFAEF4), contentColor = Color.White),
                modifier = Modifier.padding()
            ) {
                androidx.compose.material3.Text(text = "Добавить", fontSize = 22.sp)
            }
        }
    }
}

object Type {
    const val product = "Продукт"
    const val dish = "Блюдо"
}

@Composable
fun ResultKalorie(result:Int){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .offset(y=35.dp)
    ){
        androidx.compose.material3.Text(
            text = "Итоговое количество калорий",
            color = Color(0xff544161),
            fontSize = 20.sp
        )
        androidx.compose.material3.Text(
            text = "$result",
            color = Color(0xff544161),
            fontSize = 20.sp
        )

    }
}

@OptIn(ExperimentalMaterialApi::class, ExperimentalMaterial3Api::class)
@Composable
fun Demo_ExposedDropdownMenuBox() {
    val context = LocalContext.current
    val coffeeDrinks = arrayOf("Americano", "Cappuccino", "Espresso", "Latte", "Mocha", "Mocha", "Mocha", "Mocha", "Mocha", "Mocha", "Mocha")
    var expanded by remember { mutableStateOf(false) }
    var searchText by remember { mutableStateOf("") }
    var selectedText by remember { mutableStateOf(coffeeDrinks[0]) }

    Box(modifier = Modifier
        .fillMaxWidth()
        .padding(top=32.dp)
        .offset (),
    ) {
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
                searchText = "" // Очистить текст при открытии dropdown
            }
        ) {
            TextField(
                value = searchText,
                onValueChange = {
                    searchText = it
                },
                readOnly = false,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = Color(0xffEFF2FF)
                ),
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
            )

            if (expanded) {
                Column(
                    modifier = Modifier
                        .padding(top = 56.dp)
                        .height(200.dp)
                        .verticalScroll(rememberScrollState())
                ) {
                    coffeeDrinks.filter { it.contains(searchText, ignoreCase = true) }
                        .forEach { item ->
                            DropdownMenuItem(
                                onClick = {
                                        searchText = item // Установить searchText равным выбранному элементу
                                        expanded = false
                                        Toast.makeText(context, item, Toast.LENGTH_SHORT).show()
                                }
                            ) {
                                Text(text = item)
                            }
                        }
                }
            }
        }
    }
}