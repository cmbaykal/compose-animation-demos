package com.mrbaikal.composeanimationdemo.screen.demo1

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.boundsInParent
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.ui.theme.redColor

/*
    Simple Size Change Animation

    - For getting max width and height value ->
        - BoxWithConstraints -> weak performance
        - onGloballyPositioned modifier extension performance -> better performance but wait for draw

    - For understand of state animations, used animation states were connected to each other
 */

val list1 = listOf(
    "Section Item 1",
    "Section Item 2",
    "Section Item 3",
    "Section Item 4",
    "Section Item 5",
    "Section Item 6",
    "Section Item 7",
    "Section Item 8",
    "Section Item 9",
    "Section Item 10",
)

@Composable
fun Demo1() {
    var maxWidth by remember { mutableIntStateOf(0) }
    var maxHeight by remember { mutableIntStateOf(0) }
    val maxWidthDp = with(LocalDensity.current) { maxWidth.toDp() }
    val maxHeightDp = with(LocalDensity.current) { maxHeight.toDp() }


    var leftDrawerState by remember { mutableStateOf(false) }
    var bottomDrawerState by remember { mutableStateOf(false) }

    val leftDrawerWidth by animateDpAsState(
        targetValue = if (leftDrawerState) 150.dp else 0.dp,
        animationSpec = tween(400),
        label = "Left Drawer Width Animation"
    )
    val bottomDrawerHeight by animateDpAsState(
        targetValue = if (bottomDrawerState) 300.dp else 0.dp,
        animationSpec = tween(400),
        label = "Bottom Drawer Height Animation"
    )

    val contentWidth by animateDpAsState(
        targetValue = if (leftDrawerState) (maxWidthDp - leftDrawerWidth) else maxWidthDp,
        animationSpec = tween(400),
        label = "Content Width Animation"
    )
    val contentHeight by animateDpAsState(
        targetValue = if (bottomDrawerState) (maxHeightDp - bottomDrawerHeight) else maxHeightDp,
        animationSpec = tween(400),
        label = "Content Height Animation"
    )
    val contentXOffset by animateDpAsState(
        targetValue = if (leftDrawerState) leftDrawerWidth else 0.dp,
        animationSpec = tween(400),
        label = "Content X Offset Animation"
    )

    Box(modifier = Modifier
        .fillMaxSize()
        .onGloballyPositioned {
//            boundsInRoot() -> for actual device sizes
//            boundsInWindow() -> for actual device sizes with cutout
            maxWidth = it.boundsInParent().width.toInt()
            maxHeight = it.boundsInParent().height.toInt()
        }
    ) {
        Box(
            modifier = Modifier
                .offset(contentXOffset)
                .size(contentWidth, contentHeight)
                .background(redColor)
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = "Demo 1",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White.copy(alpha = 0.8f)
            )
            IconButton(
                modifier = Modifier.align(Alignment.TopStart),
                onClick = {
                    leftDrawerState = !leftDrawerState
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Back Icon"
                )
            }
            IconButton(
                modifier = Modifier.align(Alignment.BottomCenter),
                onClick = {
                    bottomDrawerState = !bottomDrawerState
                }
            ) {
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowUp,
                    contentDescription = "Back Icon"
                )
            }
        }
        DummyDrawer(
            modifier = Modifier
                .fillMaxHeight()
                .width(leftDrawerWidth)
                .align(Alignment.CenterStart),
            list = list1
        )
        DummyDrawer(
            modifier = Modifier
                .fillMaxWidth()
                .height(bottomDrawerHeight)
                .align(Alignment.BottomCenter),
            list = list1
        )
    }
}

@Composable
fun DummyDrawer(modifier: Modifier, list: List<String>) {
    LazyColumn(
        modifier = Modifier
            .background(Color.DarkGray)
            .then(modifier),
        state = rememberLazyListState()
    ) {
        items(list) {
            Button(
                modifier = Modifier
                    .padding(vertical = 10.dp, horizontal = 20.dp)
                    .fillMaxWidth(),
                onClick = { }
            ) {
                Text(
                    text = it
                )
            }
        }
    }
}