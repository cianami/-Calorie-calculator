package com.example.caloriecalculator.ui.theme

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caloriecalculator.db.DbManager
import android.app.AlertDialog

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddProduct(dbManager: DbManager, goBackCallback: () -> Unit) {
    var foodname by remember { mutableStateOf("") }
    var kkal by remember { mutableStateOf("") }
    Column(
        modifier = Modifier.background(Color.White).fillMaxSize()
    ) {
        Text(
            text = "Добавь продукт в свой холодильник",
            color = Color(0xff3A6279),
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.offset(y=20.dp)
        )
        Column(
            modifier = Modifier.offset(y=40.dp),
        ) {
            Text(
                text = "Название продукта",
                color = Color(0xff473366),
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 15.dp)
            )

            TextField(
                modifier = Modifier.width(350.dp).offset(y=5.dp).padding(start = 15.dp),
                value = foodname,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = Color(0xffFFF9E9)
                ),
                placeholder = {Text("")},
                singleLine = true,
                onValueChange = { newText ->
                        foodname = newText
                }
            )

        }
        Column(
            modifier = Modifier.offset(y=60.dp)
        ) {
            Text(
                text = "Калорийность на 100 грамм",
                color = Color(0xff473366),
                fontSize = 22.sp,
                modifier = Modifier.padding(start = 15.dp)
            )
            TextField(
                modifier = Modifier.width(100.dp).offset(y=5.dp).padding(start = 15.dp),
                value = kkal,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                    containerColor = Color(0xffFFF9E9)
                ),
                placeholder = {Text("0")},
                singleLine = true,
                onValueChange = { newText ->
                    if (newText.toIntOrNull() != null||newText=="") {
                        kkal = newText
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
                        if(kkal=="")kkal="0";
                        dbManager.insertData(foodname, kkal.toInt(), true); goBackCallback()
                          },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFFAEF4), contentColor = Color.White),
                modifier = Modifier.padding(30.dp)
            ) {
                Text(text = "Добавить", fontSize = 22.sp)
            }
        }
    }
}