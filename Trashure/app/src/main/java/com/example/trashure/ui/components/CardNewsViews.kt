package com.example.trashure.ui.components

import android.view.Menu
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CardNewsViews(
    image: Int,
    description: String,
    modifier: Modifier = Modifier,
) {
    Card (
        modifier = modifier.width(118.dp),
        shape = RoundedCornerShape(8.dp)
    ){
        Column {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(66.dp)
            )
            Column(modifier = Modifier.padding(6.dp)){
                Text(
                    text = description,
                    maxLines = 2,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardNewsPreview() {
    TrashureTheme {
        CardNewsViews(
            R.drawable.sampah,
            "Dari Jawa Barat Untuk Indonesia"
        )
    }
}
