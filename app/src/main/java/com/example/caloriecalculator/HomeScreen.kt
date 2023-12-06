package com.example.caloriecalculator

import android.annotation.SuppressLint
import android.widget.ScrollView
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
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
import scrollbar
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
    Column(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)) {
        Box(
            modifier = Modifier
                .size(220.dp)
                .clip(shape)
                .background(Color(0xffF2F6F7))

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
        modifier = Modifier
            .size(height = 50.dp, width = 150.dp)
            .wrapContentSize(Alignment.Center), onClick = { onClick() },
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFBC89D), contentColor = Color.White)
    )
    {
        Text("Добавить продукты")
    }
}


@Composable
fun HomeScreen(dbManager: DbManager, userCalorieData: UserCalorieData,modifier: Modifier, navController: NavController) {
    var calorie by remember { mutableStateOf(0) }
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(60.dp))
            CurrentDate( SimpleDateFormat("d MMMM, EEEE", Locale("ru", "RU")).format(Date()))
            Spacer(modifier = Modifier.height(20.dp))
            DailyNorm(userCalorieData.result)
            Spacer(modifier = Modifier.height(20.dp))
            CircleAndInd(userCalorieData,calorie)
            Button(
                modifier = Modifier
                    .width(260.dp)
                    .height(140.dp)
                    .wrapContentSize(Alignment.Center)
                    .offset(y = 32.dp),
                onClick = { navController.navigate(Routes.DailyProduct.route) },
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFBC89D), contentColor = Color.White)
            )
            {
                Text("Добавить продукты", fontSize = 22.sp)
            }
        }
        Divider(
            color = Color(0xff3A6279),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
        )
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .scrollbar(rememberLazyListState(), false, thickness = 10.dp)
        )
        {
            item {
                val consumptions = dbManager.getAllTodayСonsumptions();
                var tempcalorie=0;
                var deleteid=-1;
                for (consumption in consumptions) {
                    val food = dbManager.findFood(consumption.id_food)
                    Card(
                        food.foodname,
                        consumption.gram,
                        food.kkal*consumption.gram/100,
                        consumption.id
                    ) {
                        deleteid = consumption.id;
                        dbManager.deleteСonsumption(deleteid);
                        //calorie=0;
                    }
                    tempcalorie+=food.kkal*consumption.gram/100;
                }
                if(tempcalorie!=calorie)calorie=tempcalorie;
                Spacer(modifier = Modifier.height(110.dp))
            }
        }
    }
}


@Composable
fun CurrentDate(today: String){
    Text(
        text = today,
        color = Color(0xff3A6279),
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

        )
        Text(
            text = "$norm",
            fontSize = 30.sp,
            color = Color(0xff544161),

        )
    }
}
fun onClick() {
    TODO("Not yet implemented")
}
