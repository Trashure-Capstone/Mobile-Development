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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.trashure.ui.theme.*

@Composable
@Preview
fun SplashScreen1(
    modifier: Modifier = Modifier
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(PrimaryBackgroundColor, PrimaryColor)
                )
            ),
    ) {
        Image(
            painterResource(id = R.drawable.trashure_logo),
            contentDescription = stringResource(id = R.string.trashure_logo),
            modifier = Modifier.size(120.dp, 128.dp)
        )
        
    }
}