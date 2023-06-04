package com.example.trashure.ui.screen.order

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun OrderFinishScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
//            .background(colorResource(id = R.Color.colorPrimary))
            .wrapContentSize(Alignment.Center)
    ) {
        Text(
            text = "Order Finish View",
            fontWeight = FontWeight.Bold,
            color = Color(0xFF1A395A),
            modifier = Modifier.align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 25.sp
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    OrderFinishScreen()
}