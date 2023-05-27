package com.example.trashure.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle


// Set of Material typography styles to start with
val Typography = Typography(
    body1 = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )
    /* Other default text styles to override
    button = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.W500,
        fontSize = 14.sp
    ),
    caption = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
    */
)

val Lato: FontFamily = FontFamily(
    Font(R.font.lato_black, weight = FontWeight.Black, style = FontStyle.Normal),
    Font(R.font.lato_bold, weight = FontWeight.Bold, style = FontStyle.Normal),
    Font(R.font.lato_extra_bold, weight = FontWeight.ExtraBold, style = FontStyle.Normal),
    Font(R.font.lato_light, weight = FontWeight.Light, style = FontStyle.Normal),
    Font(R.font.lato_medium, weight = FontWeight.Medium, style = FontStyle.Normal),
    Font(R.font.lato_regular, weight = FontWeight.Normal, style = FontStyle.Normal),
    Font(R.font.lato_thin, weight = FontWeight.Thin, style = FontStyle.Normal),
    Font(R.font.lato_italic, weight = FontWeight.Normal, style = FontStyle.Italic),
    Font(R.font.lato_black_italic, weight = FontWeight.Black, style = FontStyle.Italic),
    Font(R.font.lato_bold_italic, weight = FontWeight.Bold, style = FontStyle.Italic),
    Font(R.font.lato_light_italic, weight = FontWeight.Light, style = FontStyle.Italic),
    Font(R.font.lato_thin_italic, weight = FontWeight.Thin, style = FontStyle.Italic)
)