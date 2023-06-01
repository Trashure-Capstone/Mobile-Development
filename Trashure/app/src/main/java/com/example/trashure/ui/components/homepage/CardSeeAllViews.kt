package com.example.trashure.ui.components.homepage

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
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
fun CardSeeAllViews(
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(46.dp)
            .height(20.dp),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFF96E0B8),

    ){
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .padding(top = 2.dp)
        ) {
            Text(
                text = "See All",
                maxLines = 1,
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CardSeeAllPreview() {
    TrashureTheme {
        CardSeeAllViews()
    }
}
