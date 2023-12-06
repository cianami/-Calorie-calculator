package com.example.caloriecalculator

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.caloriecalculator.db.DbManager
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.rememberPagerState
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.Date
import java.util.Locale

@Composable
fun CircleShapeDemo() {
    ExampleBox(shape = CircleShape)
}

@Composable
fun ExampleBox(shape: Shape) {
    Column(modifier = Modifier.fillMaxWidth().wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier.size(220.dp).clip(shape).background(Color(0xffF2F6F7))

        )
    }
}

@Composable
fun CircleAndInd(userCalorieData: UserCalorieData,calorie:Int) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircleShapeDemo()
        CircularProgressIndicator(
            modifier = Modifier.size(200.dp),
            strokeWidth = 15.dp,
            progress = calorie.toFloat()/userCalorieData.result,
            color = Color(0xffDEAAFF)
        )
        Text(
            text = "$calorie",
            modifier = Modifier.offset (y=-10.dp),
            color = Color(0xff3A6279),
            fontWeight = FontWeight.Medium,
            fontSize = 43.sp

        )
        Text(
            text = "ккал",
            color = Color(0xff3A6279),
            fontWeight = FontWeight.Normal,
            fontSize = 24.sp,
            modifier = Modifier
                .offset(y = 30.dp),
        )
    }
}

@Composable
fun FilledButton() {
    Button(
        modifier = Modifier.size(height = 50.dp, width = 150.dp)
            .wrapContentSize(Alignment.Center), onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFBC89D), contentColor = Color.White)
    )
    {
        Text("Добавить продукты")
    }
}


@Composable
fun HomeScreen(userCalorieData: UserCalorieData,modifier: Modifier, navController: NavController) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier =
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CurrentDate( SimpleDateFormat("d MMMM, EEEE", Locale("ru", "RU")).format(Date()))
        DailyNorm(userCalorieData.result)
        CircleAndInd(userCalorieData,1037)
        Button(
            modifier = Modifier.width(260.dp).height(100.dp)
                .wrapContentSize(Alignment.Center)
                .offset(y = 32.dp),
            onClick = { navController.navigate(Routes.DailyProduct.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFBC89D), contentColor = Color.White)
        )
        {
            Text("Добавить продукты", fontSize = 22.sp)
        }
    }
}

@Composable
fun CurrentDate(today: String){
    Text(
        text = today,
        color = Color(0xff3A6279),
        modifier = Modifier.offset(y = -80.dp)
    )
}

@Composable
fun DailyNorm(norm: Int){
    Row (
    ){
        Text(
            text = "Ваша норма: ",
            fontSize = 27.sp,
            color = Color(0xff544161),
            modifier = Modifier.offset(y = -30.dp)
        )
        Text(
            text = "$norm",
            fontSize = 30.sp,
            color = Color(0xff544161),
            modifier = Modifier.offset(y = -30.dp)
        )
    }
}
fun onClick() {
    TODO("Not yet implemented")
}
