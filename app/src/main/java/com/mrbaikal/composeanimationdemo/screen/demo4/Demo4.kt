package com.mrbaikal.composeanimationdemo.screen.demo4

import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.R
import com.mrbaikal.composeanimationdemo.ui.theme.blueColor
import com.mrbaikal.composeanimationdemo.ui.theme.greenColor
import com.mrbaikal.composeanimationdemo.ui.theme.orangeColor
import com.mrbaikal.composeanimationdemo.ui.theme.redColor
import kotlin.math.roundToInt

@Composable
fun Demo4() {
    var maxWidth by remember { mutableIntStateOf(0) }
    var maxHeight by remember { mutableIntStateOf(0) }

    var piece1Offset by remember { mutableStateOf(Offset(50f, 200f)) }
    var piece2Offset by remember { mutableStateOf(Offset(500f, 200f)) }
    var piece3Offset by remember { mutableStateOf(Offset(50f, 1400f)) }
    var piece4Offset by remember { mutableStateOf(Offset(500f, 1400f)) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onPlaced {
                maxWidth = it.size.width
                maxHeight = it.size.height
            }
    ) {
        Icon(
            modifier = Modifier
                .offset { IntOffset(piece1Offset.x.roundToInt(), piece1Offset.y.roundToInt()) }
                .rotate(180f)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val x = piece1Offset.x - dragAmount.x
                        val y = piece1Offset.y - dragAmount.y
                        piece1Offset = Offset(x, y)
                    }
                },
            painter = painterResource(id = R.drawable.ic_puzzle_piece),
            contentDescription = "Piece 1",
            tint = redColor
        )
        Icon(
            modifier = Modifier
                .offset { IntOffset(piece2Offset.x.roundToInt(), piece2Offset.y.roundToInt()) }
                .rotate(270f)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val x = piece2Offset.x + dragAmount.y
                        val y = piece2Offset.y - dragAmount.x
                        piece2Offset = Offset(x, y)
                    }
                },
            painter = painterResource(id = R.drawable.ic_puzzle_piece),
            contentDescription = "Piece 2",
            tint = greenColor
        )
        Icon(
            modifier = Modifier
                .offset { IntOffset(piece3Offset.x.roundToInt(), piece3Offset.y.roundToInt()) }
                .rotate(90f)
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val x = piece3Offset.x - dragAmount.y
                        val y = piece3Offset.y + dragAmount.x
                        piece3Offset = Offset(x, y)
                    }
                },
            painter = painterResource(id = R.drawable.ic_puzzle_piece),
            contentDescription = "Piece 3",
            tint = orangeColor
        )
        Icon(
            modifier = Modifier
                .offset { IntOffset(piece4Offset.x.roundToInt(), piece4Offset.y.roundToInt()) }
                .pointerInput(Unit) {
                    detectDragGestures { change, dragAmount ->
                        change.consume()
                        val x = piece4Offset.x + dragAmount.x
                        val y = piece4Offset.y + dragAmount.y
                        piece4Offset = Offset(x, y)
                    }
                },
            painter = painterResource(id = R.drawable.ic_puzzle_piece),
            contentDescription = "Piece 4",
            tint = blueColor
        )
        Text(
            modifier = Modifier
                .align(Alignment.TopCenter)
                .padding(top = 20.dp),
            text = "Demo 4",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = 0.8f)
        )
        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(236.dp, 236.dp)
                .border(2.dp, Color.Black.copy(0.5f), RoundedCornerShape(16.dp))
        )
    }
}