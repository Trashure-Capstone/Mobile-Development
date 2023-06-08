package com.example.trashure.ui.components.marketplace

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlusOne
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CardMenuMarket (
    image : Int,
    title : String,
    price : String,
    modifier : Modifier = Modifier,
){
    Card (
        modifier = modifier
            .width(160.dp)
            .height(180.dp),
        border = BorderStroke(width = 1.dp, color = Color(0xFFD1D1D1)),
        shape = RoundedCornerShape(8.dp),
    ){
        Column{
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(120.dp)
            ) {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .size(100.dp)
                )
            }
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 12.dp, bottom = 10.dp, end = 12.dp)
            ) {
                Column(
                    modifier.padding(end = 20.dp)
                ){
                    Text(
                        text = title,
                        maxLines = 1,
                        fontSize = 14.sp,
                        color = Color(0xFF1A395A),
                        fontWeight = FontWeight.Bold
                    )
                    Row(
                        modifier.padding()
                    ) {
                        Image(
                            painter = painterResource(R.drawable.money),
                            contentDescription = null,
                            modifier = Modifier
                                .padding(top = 2.dp, end = 10.dp)
                                .size(14.dp)
                        )
                        Text(
                            text = price,
                            maxLines = 1,
                            fontSize = 12.sp,
                            color = Color(0xFF1A395A),
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color(0xFF1A395A),
                    modifier = Modifier
                        .align(CenterVertically)
                        .clickable { }
                )
            }

        }
    }
}

@Composable
@Preview(showBackground = true)
fun CardMenuMarketPreview(){
    TrashureTheme {
        CardMenuMarket(image = R.drawable.bottle, title = "Plastic Bottle", price = "12.000" )
    }
}