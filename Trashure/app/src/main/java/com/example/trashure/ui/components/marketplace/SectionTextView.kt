package com.example.trashure.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun SectionTextView(
    modifier: Modifier = Modifier,
    text: String,
    isSelected: Boolean
) {
        var textWidth by remember { mutableStateOf(0.dp) }
        val density = LocalDensity.current

        Card(
            modifier = modifier
                .width(82.dp)
                .height(30.dp),
            shape = RoundedCornerShape(20.dp),
            backgroundColor = if (isSelected) Color(0xFF7BBB71) else Color(0xFFF1F1F1),
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .fillMaxWidth()
            ) {
                Text(
                    modifier = Modifier.onGloballyPositioned {
                        textWidth =
                            with(density) { it.size.width.toDp() } //update text width value according to the content size
                    },
                    text = text,
                    textAlign = TextAlign.Center,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.Bold),
                    color = if (isSelected) Color.White else Color(0xFF1A395A)
                )
            }
        }

        //Show the text underline with animation
//        AnimatedVisibility(
//            visible = isSelected,
//            enter = expandHorizontally() + fadeIn(),
//            exit = shrinkHorizontally() + fadeOut()
//        ) {
//            Box(
//                Modifier
//                    .width(textWidth)
//                    .padding(top = 15.dp)
//                    .height(3.dp)
//                    .background(Color.Green)
//            ) {}
//        }
}