package com.mrbaikal.composeanimationdemo.screen.demo7

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.TweenSpec
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.ui.theme.orangeColor
import kotlinx.coroutines.delay

@Composable
fun ShipmentTrack(
    modifier: Modifier = Modifier,
    roadWidth: Dp = 30.dp,
    roadLineWidth: Dp = 2.dp,
    roadColor:Color = Color.LightGray,
    truckSize: DpSize = DpSize(20.dp, 40.dp),
    truckColor: Color = orangeColor,
    fontSize: TextUnit = 16.sp,
    fontColor: Color = Color.DarkGray,
    steps: List<String>
) {
    var maxHeight by remember { mutableFloatStateOf(0f) }

    val pathEffect = PathEffect.dashPathEffect(floatArrayOf(80f, 40f), 0f)
    val textMeasurer = rememberTextMeasurer()
    val textStyle = TextStyle(
        fontSize = fontSize,
        color = fontColor
    )

    val roadAnimation = remember { Animatable(initialValue = 1f) }

    LaunchedEffect(steps) {
        delay(1000)
        roadAnimation.animateTo(
            targetValue = 0f,
            animationSpec = TweenSpec(2000)
        )
    }

    Canvas(modifier = modifier
        .onPlaced {
            maxHeight = it.size.height.toFloat()
        }
    ) {
        drawRoundRect(
            color = roadColor,
            size = Size(roadWidth.toPx(), maxHeight),
            cornerRadius = CornerRadius(50.dp.toPx())
        )
        drawLine(
            color = Color.White,
            start = Offset((roadWidth / 2).toPx() - (roadLineWidth / 2).toPx(), -(maxHeight / 2) * roadAnimation.value),
            end = Offset((roadWidth / 2).toPx() + (roadLineWidth / 2).toPx(), maxHeight),
            strokeWidth = roadLineWidth.toPx(),
            pathEffect = pathEffect
        )
        steps.forEachIndexed { index, item ->
            val offset = Offset((roadWidth - 10.dp).toPx(), 20.dp.toPx() + (index * maxHeight / steps.size))
            val textOffset = Offset(offset.x + 30.dp.toPx(), offset.y + 2.dp.toPx())
            drawRoundRect(
                color = roadColor,
                topLeft = offset,
                size = Size(20.dp.toPx(), 30.dp.toPx()),
                cornerRadius = CornerRadius(10.dp.toPx())
            )
            drawText(
                textMeasurer = textMeasurer,
                text = item,
                topLeft = textOffset,
                style = textStyle
            )
        }
        drawRoundRect(
            color = truckColor,
            size = Size(truckSize.width.toPx(), truckSize.height.toPx()),
            topLeft = Offset(
                x = ((roadWidth - truckSize.width) / 2).toPx(),
                y = 15.dp.toPx() + (((steps.size - 1) * maxHeight / steps.size)) * roadAnimation.value
            ),
            cornerRadius = CornerRadius(5.dp.toPx()),
        )
    }
}

@Preview
@Composable
fun ShipmentTrackPreview() {
    val list = listOf(
        "Teslim Edildi",
        "Dağıtıma Çıktı",
        "Dağıtıma Hazırlanıyor",
        "Transfer Merkezine Ulaştı",
        "Transfer Merkezine Gönderiliyor",
        "Kargoya verildi",
    )

    ShipmentTrack(
        modifier = Modifier.size(250.dp, 400.dp),
        steps = list
    )
}