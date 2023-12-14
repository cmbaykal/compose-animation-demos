package com.mrbaikal.composeanimationdemo.screen.demo10

import android.view.MotionEvent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.ClipOp
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.drawText
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.rememberTextMeasurer
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.R
import kotlin.math.pow

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Demo10() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Demo 10",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = 0.8f)
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ScratchCard(
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                overlayColor = Color.DarkGray,
                baseText = "Test",
                scratchWidth = 15.dp,
                scratchThreshold = 50
            )
            ScratchCard(
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                overlayColor = Color.DarkGray,
                baseText = "Test",
                scratchWidth = 15.dp,
                scratchThreshold = 50
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ScratchCard(
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                overlayColor = Color.DarkGray,
                baseText = "Test",
                scratchWidth = 15.dp,
                scratchThreshold = 50
            )
            ScratchCard(
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                overlayColor = Color.DarkGray,
                baseText = "Test",
                scratchWidth = 15.dp,
                scratchThreshold = 50
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            ScratchCard(
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                overlayColor = Color.DarkGray,
                baseText = "Test",
                scratchWidth = 15.dp,
                scratchThreshold = 50
            )
            ScratchCard(
                modifier = Modifier
                    .size(150.dp, 100.dp)
                    .clip(RoundedCornerShape(20.dp)),
                overlayColor = Color.DarkGray,
                baseText = "Test",
                scratchWidth = 15.dp,
                scratchThreshold = 50
            )
        }
    }
}

@ExperimentalComposeUiApi
@Composable
fun ScratchCard(
    modifier: Modifier = Modifier,
    overlayColor: Color? = null,
    overlayImage: ImageBitmap? = null,
    baseColor: Color = Color.White,
    baseText: String? = null,
    baseTextStyle: TextStyle = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Bold, color = Color.Black),
    baseImage: ImageBitmap? = null,
    scratchThreshold: Int = 50,
    thresholdTolerance: Float = 0.2f,
    scratchWidth: Dp
) {
    var maxWidth by remember { mutableIntStateOf(0) }
    var maxHeight by remember { mutableIntStateOf(0) }
    var circleSize by remember { mutableFloatStateOf(0f) }

    val pointList = remember { mutableStateListOf<Offset>() }
    val currentPathState by remember { mutableStateOf(Path()) }
    val movedOffsetState = remember { mutableStateOf<Offset?>(null) }
    val textMeasurer = rememberTextMeasurer()
    val textLayoutResult = remember(baseText, baseTextStyle) {
        if (baseText != null) textMeasurer.measure(baseText, baseTextStyle) else null
    }
    var scratchedSize by remember { mutableIntStateOf(0) }
    var isThresholdExceed by remember { mutableStateOf(false) }

    LaunchedEffect(pointList.size) {
        scratchedSize = if (circleSize != 0f) {
            (pointList.size * circleSize * thresholdTolerance).toInt()
        } else {
            0
        }

        isThresholdExceed = if (scratchedSize != 0 && maxWidth != 0 && maxHeight != 0) {
            (scratchedSize / (maxWidth * maxHeight) * 100) > scratchThreshold
        } else {
            false
        }
    }

    Canvas(
        modifier = modifier
            .clipToBounds()
            .onPlaced {
                maxWidth = it.size.width
                maxHeight = it.size.height
            }
            .pointerInteropFilter {
                when (it.action) {
                    MotionEvent.ACTION_DOWN -> {
                        currentPathState.moveTo(it.x, it.y)
                    }

                    MotionEvent.ACTION_MOVE -> {
                        val offset = Offset(it.x, it.y)
                        if (!pointList.contains(offset)) {
                            pointList.add(offset)
                        }
                        movedOffsetState.value = offset
                    }
                }
                true
            }
    ) {
        circleSize = Math.PI.toFloat() * scratchWidth.toPx().pow(2)
        val imageSize = IntSize(width = maxWidth, height = maxHeight)

        if (isThresholdExceed) {
            when {
                baseText != null -> {
                    drawRect(
                        color = baseColor,
                    )
                    drawText(
                        textMeasurer = textMeasurer,
                        text = baseText,
                        style = baseTextStyle,
                        topLeft = Offset(
                            x = center.x - (textLayoutResult?.size?.width ?: 0) / 2,
                            y = center.y - (textLayoutResult?.size?.height ?: 0) / 2,
                        )
                    )
                }

                baseImage != null -> {
                    drawImage(
                        image = baseImage,
                        dstSize = imageSize
                    )
                }
            }
        } else {
            when {
                overlayColor != null -> {
                    drawRect(
                        color = overlayColor,
                    )
                }

                overlayImage != null -> {
                    drawImage(
                        image = overlayImage,
                        dstSize = imageSize,
                    )
                }
            }

            movedOffsetState.value?.let {
                currentPathState.addOval(oval = Rect(it, scratchWidth.toPx()))
            }

            clipPath(path = currentPathState, clipOp = ClipOp.Intersect) {
                when {
                    baseText != null -> {
                        drawRect(
                            color = baseColor
                        )
                        drawText(
                            textMeasurer = textMeasurer,
                            text = baseText,
                            style = baseTextStyle,
                            topLeft = Offset(
                                x = center.x - (textLayoutResult?.size?.width ?: 0) / 2,
                                y = center.y - (textLayoutResult?.size?.height ?: 0) / 2,
                            )
                        )
                    }

                    baseImage != null -> {
                        drawImage(
                            image = baseImage,
                            dstSize = imageSize
                        )
                    }
                }
            }
        }
    }
}