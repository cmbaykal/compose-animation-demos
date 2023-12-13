package com.mrbaikal.composeanimationdemo.screen.demo2

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.ui.theme.greenColor
import com.mrbaikal.composeanimationdemo.ui.theme.orangeColor

val pieChartData = listOf(
    ChartData("Data 1", 40, Color.Red),
    ChartData("Data 2", 10, Color.Blue),
    ChartData("Data 3", 50, Color.Green)
)

val barChartData = listOf(
    ChartData("Data 1", 40, Color.Red),
    ChartData("Data 2", 80, Color.Blue),
    ChartData("Data 3", 30, Color.Green),
    ChartData("Data 4", 50, Color.Red),
    ChartData("Data 5", 70, Color.Blue),
    ChartData("Data 6", 100, Color.Green)
)

@Composable
fun Demo2() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(orangeColor),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PieChart(
            modifier = Modifier.height(200.dp),
            data = pieChartData
        )
        Text(
            text = "Demo 2",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = 0.8f)
        )
        BarChart(
            modifier = Modifier.height(200.dp).padding(10.dp),
            data = barChartData
        )
    }
}