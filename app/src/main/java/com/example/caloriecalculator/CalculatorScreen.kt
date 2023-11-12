package com.example.caloriecalculator

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
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
        Modifier.fillMaxSize()
            .fillMaxWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        //horizontalAlignment = Alignment.CenterHorizontally
        horizontalAlignment = Alignment.Start
    ) {
        Text(
            text = "Рассчитай свою норму калорий",
            color = Color(0xff3A6279),
            fontSize = 26.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.offset(y = -200.dp)
        )
        Row(
            modifier = Modifier.offset(y=-180.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Возраст: ",
                color = Color(0xff473366),
                fontSize = 22.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
            var text by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.width(100.dp),
                value = text,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                onValueChange = { newText ->
                text = newText
            }
            )
            Text(
                text = " лет",
                color = Color(0xff473366),
                fontSize = 22.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )

        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y=-180.dp)
        ) {
            Text(
                text = "Пол: ",
                color = Color(0xff473366),
                fontSize = 22.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
            val selectGender = remember {
                mutableStateOf("")
            }
            Row(
            ) {
                RadioButton(
                    selected = selectGender.value == Gender.male,
                    onClick = { selectGender.value = Gender.male },
                )
                Text(Gender.male)

                RadioButton(
                    selected = selectGender.value == Gender.female,
                    onClick = { selectGender.value = Gender.female }
                )
                Text(Gender.female)
            }
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y=-160.dp)
        ) {
            Text(
                text = "Вес: ",
                color = Color(0xff473366),
                fontSize = 22.sp,
            )
            var text by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.width(100.dp),
                value = text,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                onValueChange = { newText ->
                    text = newText
                }
            )
            Text(
                text = " кг",
                color = Color(0xff473366),
                fontSize = 22.sp,
            )
        }
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.offset(y=-140.dp)
        ) {
            Text(
                text = "Рост: ",
                color = Color(0xff473366),
                fontSize = 22.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
            var text by remember { mutableStateOf("") }
            TextField(
                modifier = Modifier.width(100.dp),
                value = text,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                singleLine = true,
                onValueChange = { newText ->
                    text = newText
                }
            )
            Text(
                text = " см",
                color = Color(0xff473366),
                fontSize = 22.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
        }
        ResultText(1200)
    }
}

object Gender {
    const val male = "Мужчина"
    const val female = "Женщина"
}

@Composable
fun ResultText(result:Int){
       Column (
           horizontalAlignment = Alignment.Start
       ){
           Text(
               text = "Ваш результат: ",
               color = Color(0xff3A6279),
               fontSize = 26.sp,
               textAlign = TextAlign.Center
           )
           Text(
               text = "$result",
               color = Color(0xff3A6279),
               fontSize = 26.sp,
               textAlign = TextAlign.Center
           )

       }
}