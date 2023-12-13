package com.mrbaikal.composeanimationdemo.screen.demo9

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mrbaikal.composeanimationdemo.ui.theme.redColor

@Composable
fun CardComponent(
    modifier: Modifier = Modifier,
    nameValue: () -> String,
    numberValue: () -> String,
    dateValue: () -> String,
    cvcValue: () -> String,
    nameFocus: () -> Boolean,
    numberFocus: () -> Boolean,
    dateFocus: () -> Boolean,
    cvcFocus: () -> Boolean,
) {
    var cardWidth by remember { mutableIntStateOf(0) }
    val cardHeight = with(LocalDensity.current) { cardWidth.toDp() / 1.5f }

    val cardRotation by animateFloatAsState(
        targetValue = if (cvcFocus()) 180f else 0f,
        animationSpec = tween(800),
        label = "Card Rotation Animation"
    )
    val numberBorderWidth by animateDpAsState(
        targetValue = if (numberFocus()) 4.dp else 0.dp,
        animationSpec = tween(400),
        label = "Number Border Width Animation"
    )
    val numberBorderColor by animateColorAsState(
        targetValue = if (numberFocus()) Color.White else Color.Transparent,
        animationSpec = tween(400),
        label = "Number Border Color Animation"
    )
    val dateBorderWidth by animateDpAsState(
        targetValue = if (dateFocus()) 4.dp else 0.dp,
        animationSpec = tween(400),
        label = "Number Border Width Animation"
    )
    val dateBorderColor by animateColorAsState(
        targetValue = if (dateFocus()) Color.White else Color.Transparent,
        animationSpec = tween(400),
        label = "Number Border Color Animation"
    )
    val nameBorderWidth by animateDpAsState(
        targetValue = if (nameFocus()) 4.dp else 0.dp,
        animationSpec = tween(400),
        label = "Number Border Width Animation"
    )
    val nameBorderColor by animateColorAsState(
        targetValue = if (nameFocus()) Color.White else Color.Transparent,
        animationSpec = tween(400),
        label = "Number Border Color Animation"
    )
    val cvcBorderWidth by animateDpAsState(
        targetValue = if (cvcFocus()) 4.dp else 0.dp,
        animationSpec = tween(400),
        label = "Number Border Width Animation"
    )
    val cvcBorderColor by animateColorAsState(
        targetValue = if (cvcFocus()) Color.White else Color.Transparent,
        animationSpec = tween(400),
        label = "Number Border Color Animation"
    )

    Box(
        modifier = modifier
            .padding(40.dp)
            .graphicsLayer {
                rotationY = cardRotation
                cameraDistance = 10f * density
            }
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(20.dp), ambientColor = Color.Black)
            .clip(RoundedCornerShape(20.dp))
            .background(redColor)
            .fillMaxWidth()
            .height(cardHeight)
            .onPlaced {
                cardWidth = it.size.width
            }
    ) {
        if (cardRotation < 90f) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .wrapContentSize()
                    .align(Alignment.BottomStart),
            ) {
                Text(
                    modifier = Modifier
                        .border(
                            width = numberBorderWidth,
                            color = numberBorderColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(6.dp),
                    color = if (numberValue().isEmpty()) Color.Black else Color.White,
                    fontSize = 18.sp,
                    letterSpacing = 2.sp,
                    text = numberValue().ifEmpty { "Card Number" }
                )
                Text(
                    modifier = Modifier
                        .border(
                            width = dateBorderWidth,
                            color = dateBorderColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(6.dp),
                    color = if (dateValue().isEmpty()) Color.Black else Color.White,
                    fontSize = 14.sp,
                    text = dateValue().ifEmpty { "Expire Date" }
                )
                Text(
                    modifier = Modifier
                        .border(
                            width = nameBorderWidth,
                            color = nameBorderColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(6.dp),
                    color = if (nameValue().isEmpty()) Color.Black else Color.White,
                    fontSize = 16.sp,
                    text = nameValue().ifEmpty { "User Name" }
                )
            }
        } else {
            Column(
                modifier = Modifier
                    .padding(end = 64.dp, top = 48.dp)
                    .wrapContentSize()
                    .align(Alignment.TopEnd),
            ) {
                Text(
                    modifier = Modifier
                        .graphicsLayer {
                            rotationY = 180f
                        }
                        .border(
                            width = cvcBorderWidth,
                            color = cvcBorderColor,
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(10.dp),
                    color = if (cvcValue().isEmpty()) Color.Black else Color.White,
                    fontSize = 14.sp,
                    text = cvcValue().ifEmpty { "Security Number" }
                )
            }
        }
    }
}