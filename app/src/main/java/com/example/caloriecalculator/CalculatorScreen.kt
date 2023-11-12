package com.example.caloriecalculator
import android.app.AlertDialog
import android.content.Context

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator(context: Context,modifier: Modifier) {
    var textAge by remember { mutableStateOf("") }
    var textWeight by remember { mutableStateOf("") }
    var textHeight by remember { mutableStateOf("") }
    val selectGender = remember { mutableStateOf(Gender.male) }
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
        Modifier
            .fillMaxSize()
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

            TextField(
                modifier = Modifier.width(100.dp),
                value = textAge,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                placeholder = {Text("0")},
                singleLine = true,
                onValueChange = { newText ->
                    if (newText.toIntOrNull() != null||newText=="") {
                        textAge = newText
                    }
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

            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                RadioButton(
                    selected = selectGender.value == Gender.male,
                    onClick = { selectGender.value = Gender.male },

                )
                Text(Gender.male, fontSize = 20.sp, color = Color(0xff473366))

                RadioButton(
                    selected = selectGender.value == Gender.female,
                    onClick = { selectGender.value = Gender.female }
                )
                Text(Gender.female, fontSize = 20.sp, color = Color(0xff473366))
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
            TextField(
                modifier = Modifier.width(100.dp),
                value = textWeight,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                placeholder = {Text("0")},
                singleLine = true,
                onValueChange = { newText ->
                    if (newText.toIntOrNull() != null||newText=="") {
                        textWeight = newText
                    }
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

            TextField(
                modifier = Modifier.width(100.dp),
                value = textHeight,
                shape = RoundedCornerShape(8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent,
                ),
                placeholder = {Text("0")},
                singleLine = true,
                onValueChange = { newText ->
                    if (newText.toIntOrNull() != null||newText=="") {
                        textHeight = newText
                    }
                }
            )
            Text(
                text = " см",
                color = Color(0xff473366),
                fontSize = 22.sp,
                // modifier = Modifier.offset(y = -100.dp)
            )
        }
        ResultText(a(context,textAge,selectGender.value,textWeight, textHeight))
    }
}

fun a(context: Context, age:String, gender:String, weight:String, height:String ):Int{
    var iAge = ("0$age").toInt()
    var iWeight = ("0$weight").toInt()
    var iHeight = ("0$height").toInt()
    var result= (10*iWeight+6.25*iHeight-5*iAge).toInt();
    if(gender==Gender.female) result-=161;
    else result+=5;
  return kotlin.math.max(result,5);
}
object Gender {
    const val male = "Мужчина"
    const val female = "Женщина"
}

@Composable
fun ResultText(result:Int){
       Column (
           horizontalAlignment = Alignment.CenterHorizontally,
           modifier = Modifier
               .fillMaxWidth()
               .offset(y=-80.dp)
       ){
           Text(
               text = "Ваш результат: ",
               color = Color(0xff544161),
               fontSize = 26.sp,
               textAlign = TextAlign.Center
           )
           Text(
               text = "$result",
               color = Color(0xff544161),
               fontSize = 32.sp,
               textAlign = TextAlign.Center
           )

       }
}