package com.example.trashure.ui.components.sellpage

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CategoryWeight(
    rewardId: Long,
    count: Int,
    onProductCountChanged: (id: Long, count: Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(300.dp)
            .height(40.dp),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                6.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .width(80.dp)
                .wrapContentHeight()
                .padding(start = 10.dp, end = 10.dp)
        ) {
            Column(
                modifier = Modifier
                .weight(1.0f)
            ) {
                Text(
                    text = "Berat Sampah",
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            ProductCounter(
                orderId = rewardId,
                orderCount = count,
                onProductIncreased = { onProductCountChanged(rewardId, count + 1) },
                onProductDecreased = { onProductCountChanged(rewardId, count - 1) },
                modifier = Modifier.padding()

            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryWeighPreview() {
    TrashureTheme {
        CategoryWeight(rewardId = 2, count = 0, onProductCountChanged = { rewardId, count ->} )
    }
}