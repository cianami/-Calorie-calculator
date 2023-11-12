package com.example.caloriecalculator
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun BottomBar(changeScreenState: (Screen) -> Unit) {
    BottomAppBar(
        actions = {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ){
                IconButton(onClick = { changeScreenState(Screen.Fridge)}) {
                    Icon(
                        Icons.Outlined.Kitchen,
                        contentDescription = "Localized description",
                        tint = Color(0xff473366) )
                }
                IconButton(onClick = { changeScreenState(Screen.Home) }) {
                    Icon(
                        Icons.Outlined.Home,
                        contentDescription = "Localized description",
                        tint = Color(0xff473366)
                    )
                }
                IconButton(onClick = { changeScreenState(Screen.Calculator) }) {
                    Icon(
                        Icons.Outlined.SquareFoot,
                        contentDescription = "Localized description",
                        tint = Color(0xff473366)
                    )
                }
            }
        },
        modifier = Modifier.height(50.dp),

    )

}