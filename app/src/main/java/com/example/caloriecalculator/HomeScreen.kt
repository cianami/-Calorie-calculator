package com.example.caloriecalculator

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
fun CircleAndInd(calorie:Int) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        CircleShapeDemo()
        CircularProgressIndicator(
            modifier = Modifier.size(200.dp),
            strokeWidth = 15.dp,
            progress = 0.3f,
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
fun HomeScreen(modifier: Modifier) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier =
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "30 октября, понедельник",
            color = Color(0xff3A6279),
            modifier = Modifier.offset(y = -100.dp)
        )
       CircleAndInd(1037)
        Button(
            modifier = Modifier.width(260.dp).height(100.dp)
                .wrapContentSize(Alignment.Center).offset(y = 32.dp), onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFBC89D), contentColor = Color.White)
        )
        {
            Text("Добавить продукты", fontSize = 22.sp)
        }
        Card("Каша гречневая с курицей", 200, 265)
        Card("Творог 1%", 100, 229)
    }
}

fun onClick() {
    TODO("Not yet implemented")
}

@Composable
@Preview
fun HomeScreenPreview(){
    HomeScreen(Modifier)
}