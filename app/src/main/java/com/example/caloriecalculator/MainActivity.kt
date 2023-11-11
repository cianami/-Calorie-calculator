package com.example.caloriecalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.caloriecalculator.ui.theme.CalorieCalculatorTheme
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CalorieCalculatorTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = Color(0xffF2F6F7)) {
                    Scaffold (topBar = {MediumTopAppBarExample()}) {
                        RightPosition(it)
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

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
fun CircleAndInd() {
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
            text = "1037",
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
fun FirstCard() {
    Card(
        colors = CardDefaults.cardColors(
            Color(0xffF1E4FB)
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 90.dp)
            .offset(y = 30.dp)
    ) {
        Text(
            text = "Каша гречневая с курицей",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = Color(0xff2B4859)
        )
        Text(
            text = "200 г. - 265 ккал",
            modifier = Modifier.offset(x = 17.dp),
            color = Color(0xff2B4859)
        )
    }
}

@Composable
fun SecondCard() {
    Card(
        colors = CardDefaults.cardColors(
            Color(0xffF1E4FB)
        ),
        modifier = Modifier
            .size(width = 360.dp, height = 90.dp)
            .offset(y = 40.dp)
    ) {
        Text(
            text = "Творог 18%",
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(16.dp),
            textAlign = TextAlign.Center,
            color = Color(0xff2B4859)
        )
        Text(
            text = "100 г. - 229 ккал",
            modifier = Modifier.offset(x = 17.dp),
            color = Color(0xff2B4859)
        )
    }
}

@Composable
fun RightPosition(paddingValues: PaddingValues) {
    var showDialog by remember { mutableStateOf(false) }
    Column(
        modifier =
        Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "30 октября, понедельник",
            color = Color(0xff3A6279),
            modifier = Modifier.offset(y = -40.dp)
        )
        CircleAndInd()
        Button(
            modifier = Modifier.width(260.dp).height(100.dp)
                .wrapContentSize(Alignment.Center).offset(y = 32.dp), onClick = { showDialog = true },
            colors = ButtonDefaults.buttonColors(containerColor = Color(0xffFBC89D), contentColor = Color.White)
        )
        {
            Text("Добавить продукты", fontSize = 22.sp)
        }
        FirstCard()
        SecondCard()
    }
}

fun onClick() {
    TODO("Not yet implemented")
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MediumTopAppBarExample() {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior(rememberTopAppBarState())
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    val scope = rememberCoroutineScope()

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            MediumTopAppBar(
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    //containerColor = MaterialTheme.colorScheme.primaryContainer,
                    containerColor = Color(0xffDCE4E7),
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                },
                navigationIcon = {
                    IconButton(onClick = {
                        scope.launch {
                            drawerState.apply {
                                if (isClosed) open() else close()
                            }
                        }
                    }) {
                        Icon(
                            imageVector = Icons.Filled.Menu,
                            contentDescription = "Localized description"
                        )
                    }
                },
                scrollBehavior = scrollBehavior
            )
        },
    ) {
        ModalNavigationDrawer(
            drawerState = drawerState,
            drawerContent = {
                ModalDrawerSheet {
                    Text("Drawer title", modifier = Modifier.padding(16.dp))
                    Divider()
                    NavigationDrawerItem(
                        label = { Text(text = "Drawer Item") },
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                    // ...other drawer items
                }
            }
        ) {
            // Screen content
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MediumTopAppBarExample()
}