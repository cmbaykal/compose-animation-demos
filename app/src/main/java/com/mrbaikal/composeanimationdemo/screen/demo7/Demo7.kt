package com.mrbaikal.composeanimationdemo.screen.demo7

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


val list = listOf(
    "Teslim Edildi",
    "Dağıtıma Çıktı",
    "Dağıtıma Hazırlanıyor",
    "Transfer Merkezine Ulaştı",
    "Transfer Merkezine Gönderiliyor",
    "Kargoya verildi",
)

@Composable
fun Demo7() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        Text(
            text = "Demo 7",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray.copy(alpha = 0.8f)
        )
        ShipmentTrack(
            modifier = Modifier
                .padding(10.dp)
                .size(250.dp, 400.dp),
            steps = list
        )
    }
}