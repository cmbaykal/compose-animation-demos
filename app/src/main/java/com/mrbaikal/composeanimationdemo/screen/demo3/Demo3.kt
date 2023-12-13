package com.mrbaikal.composeanimationdemo.screen.demo3

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.Spring.DampingRatioMediumBouncy
import androidx.compose.animation.core.Spring.StiffnessLow
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.R
import com.mrbaikal.composeanimationdemo.ui.theme.darkBlueColor
import com.mrbaikal.composeanimationdemo.ui.theme.greenColor
import com.mrbaikal.composeanimationdemo.ui.theme.orangeColor
import kotlinx.coroutines.delay

@Composable
fun Demo3() {
    var completeState by remember { mutableStateOf(false) }

    LaunchedEffect(completeState) {
        delay(2000)
        completeState = true
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Demo 3",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = 0.8f)
        )
        Button(onClick = { completeState = false }) {
            Text(text = "Reset")
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Loader1(completeState)
            Loader2()
        }
    }
}

@Composable
fun Loader1(
    completeState: Boolean
) {
    val loaderScale by animateFloatAsState(
        targetValue = if (completeState) 0f else 1f,
        animationSpec = tween(200),
        label = "Loader Scale Animation"
    )
    val tickScale by animateFloatAsState(
        targetValue = if (completeState) 1f else 0f,
        animationSpec = spring(
            dampingRatio = DampingRatioMediumBouncy,
            stiffness = StiffnessLow
        ),
        label = "Tick Scale Animation"
    )

    Box(
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier
                .size(100.dp)
                .scale(loaderScale),
            color = orangeColor,
            strokeWidth = 10.dp
        )
        Icon(
            modifier = Modifier
                .size(100.dp)
                .scale(tickScale),
            painter = painterResource(id = R.drawable.ic_tick),
            tint = greenColor,
            contentDescription = "Tick Icon"
        )
    }
}

@Composable
fun Loader2() {
    val infiniteTransition = rememberInfiniteTransition(label = "Loader Animation")
    val animationDuration = 2000

    val circle1Alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, delayMillis = 500),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Alpha Animation"
    )
    val circle1Scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, delayMillis = 500),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Scale Animation"
    )
    val circle2Alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, delayMillis = 1000),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Alpha Animation"
    )
    val circle2Scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, delayMillis = 1000),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Scale Animation"
    )
    val circle3Alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, delayMillis = 1500),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Alpha Animation"
    )
    val circle3Scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, delayMillis = 1500),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Scale Animation"
    )
    val circle4Alpha by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 0f,
        animationSpec = infiniteRepeatable(
            animation = tween(animationDuration, delayMillis = 2000),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Alpha Animation"
    )
    val circle4Scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = 1f,
        animationSpec = infiniteRepeatable(
            animation = tween(durationMillis = animationDuration, delayMillis = 2000),
            repeatMode = RepeatMode.Restart
        ), label = "Circle Scale Animation"
    )

    Box(
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier.size(150.dp)
        ) {
            drawCircle(
                color = darkBlueColor,
                radius = 75.dp.toPx() * circle1Scale,
                alpha = circle1Alpha
            )
            drawCircle(
                color = darkBlueColor,
                radius = 75.dp.toPx() * circle2Scale,
                alpha = circle2Alpha
            )
            drawCircle(
                color = darkBlueColor,
                radius = 75.dp.toPx() * circle3Scale,
                alpha = circle3Alpha
            )
            drawCircle(
                color = darkBlueColor,
                radius = 75.dp.toPx() * circle4Scale,
                alpha = circle4Alpha
            )
        }
        Icon(
            modifier = Modifier.size(30.dp),
            painter = painterResource(id = R.drawable.ic_bluetooth),
            tint = Color.White,
            contentDescription = "Tick Icon"
        )
    }
}