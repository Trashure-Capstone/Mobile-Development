package com.example.trashure.ui.components.profilepage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.TrashureTheme


@Composable
fun CardProfileViews(
    isLoading: Boolean,
    image : Int,
    username : String,
    email : String,
    role : String,
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
                if(isLoading){
                    Box(modifier = Modifier.fillMaxWidth(), contentAlignment = Alignment.Center){
                        CircularProgressIndicator(
                            color = PrimaryColor
                        )
                    }
                } else {
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
                        text = role,
                        maxLines = 10,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                
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
            isLoading = true,
            image = R.drawable.avatarss,
            username = "M Hilal Alhamdy",
            email = "mhdhilalhmdy15@gmail.com",
            role ="user" )
    }
}