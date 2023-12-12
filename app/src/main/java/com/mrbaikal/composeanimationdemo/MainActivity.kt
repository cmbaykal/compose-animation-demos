package com.mrbaikal.composeanimationdemo

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.mrbaikal.composeanimationdemo.navigation.MainNavigation
import com.mrbaikal.composeanimationdemo.ui.theme.ComposeAnimationDemoTheme

@OptIn(ExperimentalMaterial3Api::class)
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val navController = rememberNavController()

            ComposeAnimationDemoTheme {
                Scaffold(
                    topBar = { AppToolbar(navController) }
                ) {
                    NavHost(
                        navController = navController,
                        startDestination = "demoList",
                        modifier = Modifier.padding(it)
                    ) {
                        MainNavigation(navController)
                    }
                }
            }
        }
    }
}

@ExperimentalMaterial3Api
@Composable
fun AppToolbar(navController: NavHostController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route ?: ""

    TopAppBar(
        modifier = Modifier
            .shadow(
                elevation = 8.dp,
                ambientColor = Color.Black,
                spotColor = Color.Black
            ),
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Color.DarkGray,
            titleContentColor = Color.White,
            navigationIconContentColor = Color.White
        ),
        title = {
            Text(
                text = "Compose Animation Demos",
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp
            )
        },
        navigationIcon = {
            if (currentRoute != "demoList") {
                IconButton(onClick = {
                    navController.navigateUp()
                }) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = "Back Icon"
                    )
                }
            }
        }
    )
}