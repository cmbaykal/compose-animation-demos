package com.mrbaikal.composeanimationdemo.screen.demo5

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.ui.theme.darkBlueColor
import com.mrbaikal.composeanimationdemo.ui.theme.purpleColor
import de.apuri.physicslayout.lib.BodyConfig
import de.apuri.physicslayout.lib.PhysicsLayout
import de.apuri.physicslayout.lib.physicsBody
import de.apuri.physicslayout.lib.simulation.rememberSimulation
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun Demo5() {
    val coroutineScope = rememberCoroutineScope()
    val density = LocalDensity.current
    val simulation = rememberSimulation()

    var maxWidth by remember { mutableIntStateOf(0) }
    var maxHeight by remember { mutableIntStateOf(0) }
    val bottomHeight = 200.dp
    val bottomHeightPx = with(density) { bottomHeight.toPx() }
    val maxDragPx = with(density) { 120.dp.toPx() }

    val circleSize = 60.dp
    val circleSizePx = with(density) { circleSize.toPx() }
    var circleStaticState by remember { mutableStateOf(true) }
    var circleDragState by remember { mutableStateOf(true) }
    var circleOffsetX by remember { mutableIntStateOf((maxWidth / 2 - circleSizePx / 2).toInt()) }
    var circleOffsetY by remember { mutableIntStateOf((maxHeight - bottomHeightPx).toInt()) }
    val resetAnimation = remember { Animatable(0f) }

    val productCircleSize = 100.dp
    val productCircleSizePx = with(density) { productCircleSize.toPx() }
    var productCircleStaticState by remember { mutableStateOf(true) }

    val reset = {
        if (circleOffsetY > maxHeight - maxDragPx.toInt()) {
            coroutineScope.launch {
                circleStaticState = false
                circleDragState = false
                simulation.setGravity(Offset(0f, -800f))
                delay(50)
                productCircleStaticState = false
            }
        } else {
            coroutineScope.launch {
                resetAnimation.snapTo(0f)
                resetAnimation.animateTo(
                    targetValue = circleOffsetY - (maxHeight - bottomHeightPx),
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioHighBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ) {
                    if (value == circleOffsetY - (maxHeight - bottomHeightPx)) {
                        coroutineScope.launch {
                            circleOffsetY -= resetAnimation.value.toInt()
                            resetAnimation.snapTo(0f)
                        }
                    }
                }
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(darkBlueColor)
            .onPlaced {
                maxWidth = it.size.width
                maxHeight = it.size.height
                circleOffsetX = (maxWidth / 2 - circleSizePx / 2).toInt()
                circleOffsetY = (maxHeight - bottomHeightPx).toInt()
            }
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = "Demo 5",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = 0.8f)
        )

        GravitySensor { (x, y) ->
            simulation.setGravity(Offset(-x, y).times(3f))
        }

        Canvas(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(bottomHeight - circleSize / 2)
                .background(Color.White),
        ) {
            drawArc(
                color = darkBlueColor,
                startAngle = 0f,
                sweepAngle = 180f,
                useCenter = false,
                topLeft = Offset(0f, -(size.height / 2)),
                size = Size(size.width, 250f)
            )
        }

        PhysicsLayout(
            modifier = Modifier.fillMaxSize(),
            simulation = simulation
        ) {
            Card(
                modifier = Modifier
                    .size(100.dp)
                    .offset {
                        IntOffset((maxWidth / 2 - productCircleSizePx / 2).toInt(), 200)
                    }
                    .physicsBody(
                        id = "1",
                        shape = CircleShape,
                        bodyConfig = BodyConfig(
                            isStatic = productCircleStaticState,
                            angularDamping = 0.5f
                        ),
                    ),
                colors = CardDefaults.cardColors(containerColor = purpleColor),
                shape = CircleShape,
            ) {

            }
            Card(
                modifier = Modifier
                    .size(circleSize)
                    .offset {
                        IntOffset(circleOffsetX, circleOffsetY - resetAnimation.value.toInt())
                    }
                    .physicsBody(
                        id = "2",
                        shape = CircleShape,
                        bodyConfig = BodyConfig(
                            isStatic = circleStaticState,
                            angularDamping = 0.5f
                        ),
                    )
                    .pointerInput(Unit) {
                        detectDragGestures(
                            onDrag = { change, drag ->
                                change.consume()
                                if (drag.y > 0 && circleOffsetY <= maxHeight - maxDragPx) {
                                    val y = circleOffsetY + drag.y
                                    if (circleDragState) {
                                        circleOffsetY = y.toInt()
                                    }
                                }
                            },
                            onDragCancel = {
                                reset.invoke()
                            },
                            onDragEnd = {
                                reset.invoke()
                            }
                        )
                    },
                shape = CircleShape,
                colors = CardDefaults.cardColors(containerColor = Color.Red),
            ) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        textAlign = TextAlign.Center,
                        text = "ÖDE",
                        fontSize = 12.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}