package com.example.trashure.ui.components.sellpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CategoryPhoto(
    onClick:()->Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(310.dp)
            .height(40.dp)
            .clickable {
                onClick()
            },
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
            Image(
                painter = painterResource(R.drawable.camerafill),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(20.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 6.dp)
            ) {
                Text(
                    text = "Upload foto sampah",
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CategoryPhotoPreview() {
    TrashureTheme {
        CategoryPhoto({})
    }
}