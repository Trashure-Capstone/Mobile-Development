package com.example.trashure.ui.components.sellpage

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun ProductCounter(
    orderCount: Int,
    onProductIncreased: () -> Unit,
    onProductDecreased: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .size(width = 110.dp, height = 32.dp)
            .padding(4.dp)
    ) {
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.primary,
            modifier = Modifier.size(24.dp)
        ) {
            Text(
                text = "—",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductDecreased()
                    }
            )
        }
        Text(
            text = orderCount.toString() ,
            modifier = Modifier
                .weight(1f),
            fontSize = 20.sp,
            color = Color(0xFF1A395A),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold
        )
        Surface(
            shape = RoundedCornerShape(size = 5.dp),
            color = MaterialTheme.colors.primary,
            contentColor = MaterialTheme.colors.primary,
            modifier = Modifier.size(24.dp)
        ) {
            Text(
                text = "＋",
                fontSize = 16.sp,
                textAlign = TextAlign.Center,
                color = Color.White,
                modifier = Modifier
                    .weight(1f)
                    .clickable {
                        onProductIncreased()
                    }
            )
        }
    }
}

@Preview
@Composable
fun ProductCounterPreview() {
    TrashureTheme {
        ProductCounter(
            orderCount = 0,
            onProductIncreased = { },
            onProductDecreased = { }
        )
    }
}
