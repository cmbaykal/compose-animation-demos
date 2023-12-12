package com.mrbaikal.composeanimationdemo.screen.demo1

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

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