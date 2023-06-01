package com.example.trashure.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trashure.R

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.ui.theme.*

@Composable
@Preview
fun SplashScreen2(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PrimaryBackgroundColor, PrimaryColor)
                )
            ),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Spacer(
                modifier = Modifier.height(200.dp)
            )
            Image(
                painterResource(id = R.drawable.image_splash),
                contentDescription = stringResource(id = R.string.image_splash),
                contentScale = ContentScale.None,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(282.dp, 158.dp)
            )
            Spacer(
                modifier = Modifier.height(30.dp)
            )
            Text(
                text = stringResource(id = R.string.tagline_1),
                fontFamily = Lato,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Text(
                text = stringResource(id = R.string.tagline_2),
                fontFamily = Lato,
                fontSize = 24.sp,
                fontWeight = FontWeight.ExtraBold
            )
            Spacer(
                modifier = Modifier.height(80.dp)
            )
            Button(
                shape = RoundedCornerShape(15.dp),
                enabled = true,
                colors = ButtonDefaults.buttonColors(
                    containerColor = PrimaryBackgroundColor,
                    contentColor = PrimaryTextColor,
                    SecondaryBackgroundColor,
                    SecondaryTextColor),
                onClick = { /*TODO*/ }) {
                Text(text = stringResource(id = R.string.sign_in_now))
            }
        }
        
    }
}