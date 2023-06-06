package com.example.trashure.ui.components.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CardMenuProfileViews (
    modifier: Modifier = Modifier
){
    Card(
        modifier = modifier
            .width(340.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(10.dp)
    ){
        Text(
            text = "Menu",
            maxLines = 1,
            fontSize = 14.sp,
            fontWeight = FontWeight.ExtraBold,
            modifier = Modifier
                .padding(start=20.dp, top = 16.dp, end = 20.dp )
        )
        Column(
            verticalArrangement = Arrangement.spacedBy(6.dp),
            modifier = modifier
                .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
        ) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 24.dp)
            ) {
                Row(
                    modifier = modifier
                        .padding(end = 72.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_profile),
                        contentDescription = null,
                        modifier = modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "Edit Profile",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = modifier
                            .align(CenterVertically)
                    )
                }

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_profile),
                        contentDescription = null,
                        modifier = modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "Edit Profile",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = modifier
                            .align(CenterVertically)
                    )
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = modifier
                        .padding(end = 72.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_profile),
                        contentDescription = null,
                        modifier = modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "Edit Profile",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = modifier
                            .align(CenterVertically)
                    )
                }

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_profile),
                        contentDescription = null,
                        modifier = modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "Edit Profile",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = modifier
                            .align(CenterVertically)
                    )
                }
            }

            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(top = 10.dp)
            ) {
                Row(
                    modifier = modifier
                        .padding(end = 72.dp)
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_profile),
                        contentDescription = null,
                        modifier = modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "Edit Profile",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = modifier
                            .align(CenterVertically)
                    )
                }

                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.edit_profile),
                        contentDescription = null,
                        modifier = modifier
                            .size(30.dp)
                            .padding(end = 10.dp)
                    )
                    Text(
                        text = "Edit Profile",
                        fontWeight = FontWeight.Medium,
                        fontSize = 12.sp,
                        modifier = modifier
                            .align(CenterVertically)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun CardMenuProfilePreviews(){
    TrashureTheme {
        CardMenuProfileViews()
    }
}

