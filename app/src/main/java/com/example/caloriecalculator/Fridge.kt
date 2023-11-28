package com.example.caloriecalculator

import androidx.compose.foundation.ExperimentalFoundationApi
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
import androidx.compose.ui.window.Dialog
import com.example.caloriecalculator.ui.theme.Tab
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.caloriecalculator.ui.theme.AddProduct

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Fridge(modifier: Modifier) {
    MainScreen()
}

@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun MainScreen(){
    val tabs = listOf(
        Tab.ScreenProducts,
        Tab.ScreenDishes
    )

    val pagerState = rememberPagerState(pageCount = tabs.size)

    androidx.compose.material.Scaffold {
        Tabs(tabs = tabs, pagerState = pagerState)
        TabsContent(tabs = tabs, pagerState = pagerState)
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<Tab>, pagerState: PagerState){
val scope = rememberCoroutineScope()
    androidx.compose.material.TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color(0xffB7C1FC),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ){
        tabs.forEachIndexed{ index, tab ->
            androidx.compose.material.LeadingIconTab(
                text = { Text(text = tab.title)},
                icon = {null},
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                    }
                }
            )
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabsContent(tabs: List<Tab>, pagerState: PagerState){
HorizontalPager(state = pagerState){ page ->
    tabs[page].screen()

}
}
@Composable
fun Products(){
    val     navController = rememberNavController()

    NavigationComposeTheme{

    }
}

@Composable
fun Dishes(){
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Button(
            onClick = { /* Handle button click */ },
            modifier = Modifier.padding(55.dp)
        ) {
        }
    }
}