package com.example.trashure.ui.components.scanpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.components.homepage.CardMenuViews
import com.example.trashure.ui.theme.Green_1
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CardInformationViews(
//    icon: Int,
    title: String,
    description: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier
            .width(340.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color(0xFFCCEFD9)
    ){
        Row(
            modifier = modifier
                .width(80.dp)
                .padding(start = 14.dp, top = 12.dp, bottom = 12.dp, end = 14.dp)
        ){
            Image(
                painter = painterResource(R.drawable.recycling),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 3.dp)
                    .size(30.dp)
            )
            Column( modifier = Modifier
                .padding(start = 16.dp, top = 2.dp)
                .weight(1.0f)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = description,
                    maxLines = 10,
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardMenuPreview() {
    TrashureTheme {
        CardInformationViews(
            "Jual Sampah",
            "Bersihkan lingkunganmu sekarang gkunganmu sekaranggkunganmu sekarang gkunganmu sekarang "
        )
    }
}
