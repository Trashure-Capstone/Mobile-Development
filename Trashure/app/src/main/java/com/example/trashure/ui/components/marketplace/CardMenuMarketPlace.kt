package com.example.trashure.ui.components.marketplace

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.material3.CardColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme
import okhttp3.internal.wait

@Composable
fun CardMenuMarketPlace (
    icon : Int,
    title : String,
    modifier: Modifier = Modifier
){
    Card (
        modifier = modifier
            .width(120.dp)
            .height(120.dp),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(width = 1.dp, color = Color.Black)

    ){
        Column(
            verticalArrangement = Arrangement.spacedBy(12.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxWidth()
        ) {
            Image(
                painter = painterResource(icon),
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
            )
            Text(
                text = title,
                maxLines = 1,
                fontSize = 14.sp,
                fontWeight = FontWeight.ExtraBold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardMenuMarketPlacePreview(){
    TrashureTheme {
        CardMenuMarketPlace(
            R.drawable.shopping_basket,
             "Trashure Market"
        )
    }
}