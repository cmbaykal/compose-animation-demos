package com.mrbaikal.composeanimationdemo.screen.demo5

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.util.Log
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.animateIntOffsetAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onPlaced
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.IntOffset
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver

@Composable
fun GravityLayout(
    offset: IntOffset = IntOffset(0, 0),
    increaseAmount: Int = 40,
    content: @Composable BoxScope.() -> Unit
) {
    var maxHeight by remember { mutableIntStateOf(0) }
    var maxWidth by remember { mutableIntStateOf(0) }
    var contentWidth by remember { mutableIntStateOf(0) }
    var contentHeight by remember { mutableIntStateOf(0) }

    val lifecycleOwner = LocalLifecycleOwner.current
    val context = LocalContext.current
    val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    val gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)

    var changedOffset by remember { mutableStateOf(offset) }
    val currentOffset by animateIntOffsetAsState(
        targetValue = changedOffset,
        animationSpec = tween(400),
        label = "Offset Animation"
    )

    val sensorEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent?) {
            if (event != null) {
                val axisX: Float = event.values[0]
                val axisY: Float = event.values[1]
                val axisZ: Float = event.values[2]

                Log.d("Sensor Motion", "Axis -> $axisX, $axisY, $axisZ")

                val x = if (axisX > 0) -increaseAmount else increaseAmount
                val y = if (axisY > 0) increaseAmount else -increaseAmount
                val z = if (axisZ > 0) 1 else -1

                val calculatedX = when {
                    currentOffset.x + x < 0f -> {
                        0f
                    }

                    currentOffset.x + x >= maxWidth - contentWidth -> {
                        maxWidth - contentWidth
                    }

                    else -> {
                        currentOffset.x + x
                    }
                }
                val calculatedY = when {
                    currentOffset.y + y < 0f -> {
                        0f
                    }

                    currentOffset.y + y >= maxHeight - contentHeight -> {
                        maxHeight - contentHeight
                    }

                    else -> {
                        currentOffset.y + y
                    }
                }


                changedOffset = IntOffset(calculatedX.toInt(), calculatedY.toInt())
            }
        }

        override fun onAccuracyChanged(p0: Sensor?, p1: Int) = Unit
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .onPlaced {
                maxWidth = it.size.width
                maxHeight = it.size.height
            }
    ) {
        Box(
            modifier = Modifier
                .wrapContentSize()
                .offset { currentOffset }
                .onPlaced {
                    contentWidth = it.size.width
                    contentHeight = it.size.height
                },
        ) {
            content()
        }
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            when (event) {
                Lifecycle.Event.ON_RESUME -> {
                    sensorManager.registerListener(sensorEventListener, gyroSensor, SensorManager.SENSOR_DELAY_NORMAL)
                }

                Lifecycle.Event.ON_PAUSE -> {
                    sensorManager.unregisterListener(sensorEventListener)
                }

                Lifecycle.Event.ON_STOP -> {
                    sensorManager.flush(sensorEventListener)
                }

                else -> {}
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}