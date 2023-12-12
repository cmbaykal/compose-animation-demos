package com.mrbaikal.composeanimationdemo.screen.demoList

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController

@Composable
fun DemoList(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo1") }
            ) {
                Text(
                    text = "1",
                    fontSize = 20.sp
                )
            }
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo2") }
            ) {
                Text(
                    text = "2",
                    fontSize = 20.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo1") }
            ) {
                Text(
                    text = "3",
                    fontSize = 20.sp
                )
            }
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo2") }
            ) {
                Text(
                    text = "4",
                    fontSize = 20.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo1") }
            ) {
                Text(
                    text = "5",
                    fontSize = 20.sp
                )
            }
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo2") }
            ) {
                Text(
                    text = "6",
                    fontSize = 20.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo1") }
            ) {
                Text(
                    text = "7",
                    fontSize = 20.sp
                )
            }
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo2") }
            ) {
                Text(
                    text = "8",
                    fontSize = 20.sp
                )
            }
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo1") }
            ) {
                Text(
                    text = "9",
                    fontSize = 20.sp
                )
            }
            Button(
                modifier = Modifier.size(80.dp),
                shape = RoundedCornerShape(20.dp),
                onClick = { navController.navigate("demo2") }
            ) {
                Text(
                    text = "10",
                    fontSize = 20.sp
                )
            }
        }
    }
}