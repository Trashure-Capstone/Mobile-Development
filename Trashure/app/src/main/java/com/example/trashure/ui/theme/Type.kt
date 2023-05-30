package com.example.trashure.ui.theme

import androidx.compose.material.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontStyle

// Font family Lato
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

// Set of Material typography styles to start with
val Typography = Typography(
    defaultFontFamily = Lato,
    h1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    ),
    h2 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 16.sp
    ),
    h3 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 14.sp
    ),
    subtitle1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    ),
    subtitle2 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.SemiBold,
        fontSize = 14.sp
    ),
    body1 = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Medium,
        fontSize = 12.sp
    ),
    caption = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Normal,
        fontSize = 10.sp
    ),
    button = TextStyle(
        fontFamily = Lato,
        fontWeight = FontWeight.Bold,
        fontSize = 14.sp
    )

)



    


