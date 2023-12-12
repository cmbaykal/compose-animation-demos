package com.mrbaikal.composeanimationdemo.screen.demo3

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.dp

@Composable
fun Demo3() {

    var selectedColor by remember { mutableStateOf(Color.Black) }
    var currentPosition by remember { mutableStateOf(Offset.Unspecified) }
    var previousPosition by remember { mutableStateOf(Offset.Unspecified) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(10.dp)
    ) {
        ColorSelectionRow(
            selectedColor = { selectedColor },
            onColorSelect = { selectedColor = it }
        )
        Canvas(
            modifier = Modifier
                .padding(top = 20.dp)
                .fillMaxSize()
                .border(1.dp, Color.Black.copy(0.5f), RoundedCornerShape(10.dp))
                .pointerInput(Unit) {

                }
        ) {

        }
    }
}

@Composable
fun ColorSelectionRow(
    selectedColor: () -> Color,
    onColorSelect: (Color) -> Unit
) {
    val current = remember(selectedColor()) { selectedColor() }

    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        Box(
            modifier = Modifier
                .size(30.dp)
                .scale(if (current == Color.Black) 1.4f else 1f)
                .clip(CircleShape)
                .background(Color.Black)
                .clickable {
                    onColorSelect.invoke(Color.Black)
                }
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .scale(if (current == Color.Red) 1.4f else 1f)
                .clip(CircleShape)
                .background(Color.Red)
                .clickable {
                    onColorSelect.invoke(Color.Red)
                }
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .scale(if (current == Color.Blue) 1.4f else 1f)
                .clip(CircleShape)
                .background(Color.Blue)
                .clickable {
                    onColorSelect.invoke(Color.Blue)
                }
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .scale(if (current == Color.Green) 1.4f else 1f)
                .clip(CircleShape)
                .background(Color.Green)
                .clickable {
                    onColorSelect.invoke(Color.Green)
                }
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .scale(if (current == Color.Yellow) 1.4f else 1f)
                .clip(CircleShape)
                .background(Color.Yellow)
                .clickable {
                    onColorSelect.invoke(Color.Yellow)
                }
        )
        Box(
            modifier = Modifier
                .size(30.dp)
                .scale(if (current == Color.DarkGray) 1.4f else 1f)
                .clip(CircleShape)
                .background(Color.DarkGray)
                .clickable {
                    onColorSelect.invoke(Color.DarkGray)
                }
        )
    }
}