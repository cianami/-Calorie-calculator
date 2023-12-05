package com.example.caloriecalculator.ui.theme

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.example.caloriecalculator.Gender
import com.example.caloriecalculator.ResultText
import com.example.caloriecalculator.UserCalorieData
import com.example.caloriecalculator.a

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
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            //modifier = Modifier.offset(y = -200.dp)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset()
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
        Column() {
            androidx.compose.material3.Text(
                text = "Введите количество грамм",
                color = Color(0xff473366),
                fontSize = 22.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )

            TextField(
                modifier = Modifier.width(100.dp),
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
                modifier = Modifier.padding(30.dp)
            ) {
                androidx.compose.material3.Text(text = "Добавить", fontSize = 22.sp)
            }
        }
        ResultKalorie(5)
    }
}

object Type {
    const val product = "Продукт"
    const val dish = "Блюдо"
}

@Composable
fun ResultKalorie(result:Int){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .offset()
    ){
        androidx.compose.material3.Text(
            text = "Итоговое количество грамм",
            color = Color(0xff544161),
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )
        androidx.compose.material3.Text(
            text = "$result",
            color = Color(0xff544161),
            fontSize = 32.sp,
            textAlign = TextAlign.Center
        )

    }
}