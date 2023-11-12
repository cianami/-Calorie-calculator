package com.example.caloriecalculator

import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Card(name:String, weight:Int, caloree:Int) {
    Card(
        colors = CardDefaults.cardColors(
            Color(0xffF1E4FB)
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 90.dp)
            .offset(y = 30.dp)
            .padding(bottom = 10.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp)
                .offset(y=-5.dp),
            textAlign = TextAlign.Center,
            color = Color(0xff2B4859)
        )
        Text(
            text = "$weight г. - $caloree ккал",
            modifier = Modifier
                .offset(x = 17.dp, y=-10.dp),
            color = Color(0xff2B4859)
        )
    }
}