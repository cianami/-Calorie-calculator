package com.example.caloriecalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(modifier: Modifier) {
//    Text(
//        text = "Calculator",
//        fontWeight = FontWeight.Bold,
//        modifier = Modifier
//            .padding(16.dp),
//        textAlign = TextAlign.Center,
//        color = Color(0xff2B4859)
//    )
    Column(
        modifier =
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Рассчитай свою норму калорий",
            color = Color(0xff3A6279),
            fontSize = 20.sp,
            // modifier = Modifier.offset(y = -100.dp)
        )
        Row() {
            Text(
                text = "Возраст: ",
                color = Color(0xff473366),
                fontSize = 16.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
            var text by remember { mutableStateOf("") }
            TextField(value = text, onValueChange = { newText ->
                text = newText
            }
            )
            Text(
                text = " лет",
                color = Color(0xff473366),
                fontSize = 16.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )

        }
        Row(){
            Text(
                text = "Пол: ",
                color = Color(0xff473366),
                fontSize = 16.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
            val selectGender = remember {
                mutableStateOf("")
            }
            Row {
                RadioButton(
                    selected = selectGender.value == Gender.male,
                    onClick = { selectGender.value = Gender.male }
                )
                Text(Gender.male)

                RadioButton(
                    selected = selectGender.value == Gender.female,
                    onClick = { selectGender.value = Gender.female }
                )
                Text(Gender.female)
            }
        }
        Row(){
            Text(
                text = "Вес: ",
                color = Color(0xff473366),
                fontSize = 16.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
            var text by remember { mutableStateOf("") }
            TextField(value = text, onValueChange = { newText ->
                text = newText
            }
            )
        }
        Row(){
            Text(
                text = "Рост: ",
                color = Color(0xff473366),
                fontSize = 16.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
            var text by remember { mutableStateOf("") }
            TextField(value = text, onValueChange = { newText ->
                text = newText
            }
            )
        }
    }
}

object Gender{
    const val male = "Male"
    const val female = "Female"
}
