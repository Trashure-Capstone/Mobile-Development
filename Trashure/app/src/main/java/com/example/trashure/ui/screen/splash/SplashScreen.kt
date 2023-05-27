package com.example.trashure.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.trashure.R

import androidx.compose.foundation.background
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.PrimaryColor

@Composable
@Preview
fun SplashScreenMain(
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
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painterResource(id = R.drawable.image_splash),
            contentDescription = stringResource(id = R.string.image_splash),
            contentScale = ContentScale.None,
            modifier = Modifier.align(Alignment.TopStart)
        )
    }
}