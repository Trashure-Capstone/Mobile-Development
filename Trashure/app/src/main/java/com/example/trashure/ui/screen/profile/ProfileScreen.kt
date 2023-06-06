package com.example.trashure.ui.screen.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.components.profile.CardMenuProfileViews
import com.example.trashure.ui.components.profile.CardProfileViews
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .height(160.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PrimaryBackgroundColor, Color(0xFFCCEFD9)
                    )
                ),
                shape = RoundedCornerShape(
                    bottomEnd = 20.dp,
                    bottomStart = 20.dp
                )
            )
    )
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spacer(modifier = Modifier.height(60.dp))
        Text(
            text = "Profile",
            maxLines = 1,
            textAlign = TextAlign.Center,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(26.dp))
        CardProfileViews(
            image = R.drawable.avatarss,
            username = "M Hilal Alhamdy",
            email = "mhdhilalhmdy15@gmail.com",
            phoneNumber ="08211221312313"
        )
        Spacer(modifier = Modifier.height(26.dp))
        CardMenuProfileViews()
        Spacer(modifier = Modifier.height(26.dp))
        ButtonLogout()
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview(){
    TrashureTheme {
        ProfileScreen()
    }
}

@Composable
fun ButtonLogout(
    modifier: Modifier = Modifier
){
    Button(
        onClick = {},
        shape = Shapes_Larger.small,
        colors = ButtonDefaults.buttonColors(Color(0xFFE53E3E)),
        modifier = modifier
            .width(140.dp)
    ) {
        Text(text = "Logout")
    }
}