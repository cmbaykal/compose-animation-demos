package com.mrbaikal.composeanimationdemo.screen.demo2

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.translate
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import kotlin.math.round

data class ChartData(
    val identifier: String,
    val point: Int,
    val color: Color
)

@Composable
fun PieChart(
    modifier: Modifier = Modifier,
    data: List<ChartData>
) {
    var maxHeight by remember { mutableIntStateOf(0) }
    val maxHeightDp = with(LocalDensity.current) { maxHeight.toDp() }

    val sum = data.sumOf { it.point }.toFloat()
    val path = Path()
    val angles = mutableListOf<Float>()

    val animation = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(data) {
        delay(1000)
        animation.animateTo(
            targetValue = 1f,
            animationSpec = TweenSpec(800)
        )
    }

    Row(
        modifier = modifier.onPlaced {
            maxHeight = it.size.height
        },
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .padding(end = 8.dp)
                .size(maxHeightDp)
        ) {
            var startAngle = 0f
            val radius = maxHeight / 2f
            val size = radius * 2
            val rect = Rect(Offset(-radius, -radius), Size(size, size))

            translate(radius, radius) {
                data.forEachIndexed { index, item ->
                    val ratio = item.point / sum
                    val maxAngle = if (index == data.lastIndex) 360f else 359f
                    val angle = round(ratio * maxAngle)
                    path.moveTo(0f, 0f)
                    path.arcTo(
                        rect = rect,
                        startAngleDegrees = startAngle,
                        sweepAngleDegrees = angle * animation.value, false
                    )
                    angles.add(angle)
                    drawPath(
                        path = path,
                        color = item.color
                    )
                    drawPath(
                        path = path,
                        style = Stroke(width = 8f),
                        color = Color.White
                    )
                    path.reset()
                    startAngle += angle
                }
                drawCircle(
                    color = Color.White,
                    center = Offset(0f, 0f),
                    radius = radius / 2
                )
            }
        }
        LazyColumn(
            modifier = Modifier.padding(start = 8.dp),
            state = rememberLazyListState(),
        ) {
            items(data) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Canvas(modifier = Modifier) {
                        drawCircle(it.color, radius = 4.dp.toPx())
                    }
                    Text(
                        modifier = Modifier.padding(start = 8.dp),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        text = it.identifier
                    )
                }
            }
        }
    }
}

@Composable
fun BarChart(
    modifier: Modifier = Modifier,
    data: List<ChartData>
) {
    var maxHeight by remember { mutableIntStateOf(0) }
    var maxWidth by remember { mutableIntStateOf(0) }
    val maxHeightDp = with(LocalDensity.current) { maxHeight.toDp() }

    val maxPoint = data.maxOf { it.point }

    val borderColor = Color.Black
    val density = LocalDensity.current
    val strokeWidth = with(density) { 1.dp.toPx() }

    val animation = remember { Animatable(initialValue = 0f) }

    LaunchedEffect(data) {
        delay(1000)
        animation.animateTo(
            targetValue = 1f,
            animationSpec = TweenSpec(800)
        )
    }

    Column(
        modifier = Modifier
    ) {
        Canvas(
            modifier = modifier
                .onPlaced {
                    maxWidth = it.size.width
                    maxHeight = it.size.height
                }
                .fillMaxWidth(),
        ) {
            drawLine(
                color = borderColor,
                start = Offset(0f, size.height),
                end = Offset(size.width, size.height),
                strokeWidth = strokeWidth
            )
            drawLine(
                color = borderColor,
                start = Offset(0f, 0f),
                end = Offset(0f, size.height),
                strokeWidth = strokeWidth
            )

            data.forEachIndexed { index, item ->
                val top = maxHeight - (item.point.toFloat() / maxPoint.toFloat()) * maxHeight
                val left = ((maxWidth.toFloat() / data.size)) * index + 16.dp.toPx()
                val width = (maxWidth.toFloat() / data.size - 16.dp.toPx())
                val height = (item.point.toFloat() / maxPoint.toFloat() * maxHeight - 1.dp.toPx()) * animation.value

                drawRoundRect(
                    color = item.color,
                    topLeft = Offset(left, top),
                    size = Size(width, height),
                    cornerRadius = CornerRadius(10f, 10f)
                )
            }
        }
        Row(
            modifier = Modifier.padding(top = 10.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            data.forEach {
                Text(
                    modifier = Modifier.padding(start = 8.dp).rotate(300f),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    text = it.identifier
                )
            }
        }
    }
}