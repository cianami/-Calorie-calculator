package com.example.caloriecalculator

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Close
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun Card(name:String, weight:Int, caloree:Int, id:Int, delete: (Int) -> Unit) {
    var isVisible by remember { mutableStateOf(true) }

    if (isVisible) {
        Card(
            colors = CardDefaults.cardColors(
                Color(0xffF1E4FB)
            ),
            modifier = Modifier
                .size(width = 360.dp, height = 90.dp)
                .offset(y = 30.dp)
                .padding(bottom = 10.dp)
        ) {
            val card = this;
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = name,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .padding(16.dp)
                            .offset(y = -5.dp),
                        textAlign = TextAlign.Center,
                        color = Color(0xff2B4859)
                    )
                }
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    IconButton(
                        onClick = { delete(id); isVisible=false},
                    ) {
                        Icon(
                            Icons.Outlined.Close,
                            contentDescription = "Localized description",
                            modifier = Modifier.size(20.dp),
                            tint = Color(0xff473366)
                        )
                    }
                }
            }
            Text(
                text = "$weight г. - $caloree ккал",
                modifier = Modifier
                    .offset(x = 17.dp, y = -10.dp),
                color = Color(0xff2B4859)
            )
        }
    }
}