package com.example.trashure.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashure.R
import androidx.compose.material.Text
import androidx.compose.ui.unit.sp
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,

){

}

@Composable
@Preview(showBackground = true)
fun HomeScreenPreview() {
    TrashureTheme {
        HomeScreen()
    }
}



@Composable
fun BannerHome(
    username: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.decoration),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(192.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Row{
                Text(
                    text = "Hello, $username",
                    maxLines = 1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Image(
                    painter = painterResource(R.drawable.wavinghand),
                    contentDescription = "null",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Text(
                text = "Sudah kamu membuang sampah?",
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TextPreview() {
    TrashureTheme {
        BannerHome("Hilalhmdy")
    }
}


