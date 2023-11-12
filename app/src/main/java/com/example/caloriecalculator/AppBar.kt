package com.example.caloriecalculator

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.modifier.modifierLocalOf
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar(modifier: Modifier) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())

    MediumTopAppBar(
        modifier= modifier,
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            //containerColor = MaterialTheme.colorScheme.primaryContainer,
            containerColor = Color(0xffDCE4E7),
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
        },
        scrollBehavior = scrollBehavior
    )

}
