package com.example.trashure.ui.components.profilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme


@Composable
fun CardProfileViews(
    image : Int,
    username : String,
    email : String,
    phoneNumber : String,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .width(340.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp)
    ){
        Row(
            modifier = modifier
                .width(80.dp)
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 34.dp)
        ){
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .weight(1.0f)
            ) {
                Text(
                    text = username,
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(
                    text = email,
                    maxLines = 10,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = phoneNumber,
                    maxLines = 10,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .align(CenterVertically)
                    .size(60.dp)
                    .clip(CircleShape)
            )
        }
    }
}


@Preview
@Composable
fun CardProfileReview(){
    TrashureTheme {
        CardProfileViews(
            image = R.drawable.avatarss,
            username = "M Hilal Alhamdy",
            email = "mhdhilalhmdy15@gmail.com",
            phoneNumber ="08211221312313" )
    }
}