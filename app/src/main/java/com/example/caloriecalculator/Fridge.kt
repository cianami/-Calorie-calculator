package com.example.caloriecalculator

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caloriecalculator.ui.theme.Tab
import com.google.accompanist.pager.*
import kotlinx.coroutines.launch
import androidx.navigation.NavController
import com.example.caloriecalculator.db.DbManager
import scrollbar

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Fridge(modifier: Modifier, navController: NavController, dbManager: DbManager) {
    MainScreen(navController, dbManager)
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalFoundationApi::class)
@ExperimentalPagerApi
@Composable
fun MainScreen(navController: NavController, dbManager: DbManager) {
    val tabs = listOf(
        Tab("Продукты") { Products(navController, dbManager) },
        Tab("Блюда") { Dishes(navController, dbManager) }
    )

    val pagerState = rememberPagerState(pageCount = tabs.size, initialPage = currentPage)

    androidx.compose.material.Scaffold {
        Column {
            Tabs(tabs = tabs, pagerState = pagerState)
            TabsContent(tabs = tabs, pagerState = pagerState)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun Tabs(tabs: List<Tab>, pagerState: PagerState){
val scope = rememberCoroutineScope()
    androidx.compose.material.TabRow(
        selectedTabIndex = pagerState.currentPage,
        backgroundColor = Color(0xffD5E1FF ),
        indicator = { tabPositions ->
            TabRowDefaults.Indicator(
                Modifier.pagerTabIndicatorOffset(pagerState, tabPositions)
            )
        }
    ){
        tabs.forEachIndexed{ index, tab ->
            androidx.compose.material.LeadingIconTab(
                text = { Text(text = tab.title)},
                icon = {},
                selected = pagerState.currentPage == index,
                onClick = {
                    scope.launch {
                        pagerState.animateScrollToPage(index)
                        currentPage = index
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
fun Products(navController: NavController, dbManager: DbManager){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        LazyColumn(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .scrollbar(rememberLazyListState(), false, thickness = 10.dp)
        )
        { item {
            val products = dbManager.getAllFoods(true)
            for (product in products) {
                Card(product.foodname, 100, product.kkal,product.id, dbManager::delete)
            }
            Spacer(modifier = Modifier.height(110.dp))
            }
        }
    }

    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    )
    {

        Button(
            onClick = { navController.navigate(Routes.Product.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFBC89D), contentColor = Color.White),
            modifier = Modifier.padding(55.dp)
        ) {
            Text(text = "Добавить продукт", fontSize = 18.sp)
        }
    }
}

@Composable
fun Dishes(navController: NavController, dbManager: DbManager){
    Box(
        contentAlignment = Alignment.TopCenter,
        modifier = Modifier
            .fillMaxSize()
    )
    {
        Column(
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
        )
        {
            val dishes=dbManager.getAllFoods(false)
            for (dish in dishes)
            {
                Card(dish.foodname, 100, dish.kkal,dish.id, dbManager::delete)
            }
            Spacer(modifier = Modifier.height(110.dp))
        }
    }
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    )
    {
        Button(
            onClick = { navController.navigate(Routes.Dish.route) },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffF26D8C), contentColor = Color.White),
            modifier = Modifier.padding(55.dp)
        ) {
            Text(text = "Добавить блюдо", fontSize = 18.sp)
        }
    }
}

var currentPage = 0