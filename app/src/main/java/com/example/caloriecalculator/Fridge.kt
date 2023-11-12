package com.example.caloriecalculator

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
@Composable
fun Fridge(modifier: Modifier) {
    Text(
        text = "Fridge",
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(16.dp),
        textAlign = TextAlign.Center,
        color = Color(0xff2B4859)
    )
}