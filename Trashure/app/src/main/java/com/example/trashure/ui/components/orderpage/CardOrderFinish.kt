package com.example.trashure.ui.components.orderpage

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CardOrderFinish(
    id : String,
    time : String,
    date : String,
    status : String,
    modifier: Modifier = Modifier
) {
    val showDialog =  remember { mutableStateOf(false) }

    if(showDialog.value)
        DialogOrderFinish(
            id = "ID11212ASAA",
            time = "17.50",
            date = "26 May 2023",
            type = "Plastik",
            coin = "300",
            earn = "2.400",
            status = "Selesai",
            onCancelClicked = { showDialog.value = false })

    Card(
        modifier = modifier
            .width(340.dp)
            .wrapContentHeight()
            .clickable {
                       showDialog.value = true
            },
        shape = RoundedCornerShape(10.dp)
    ){
        Row(
            modifier = modifier
                .width(80.dp)
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 20.dp)
        ){
            Image(
                painter = painterResource(R.drawable.vector_recycle),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(30.dp)
            )
            Column(
                verticalArrangement = Arrangement.spacedBy(6.dp),
                modifier = Modifier
                    .padding(start = 11.dp)
                    .weight(1.0f)
            ) {
                Text(
                    text = id,
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.ExtraBold
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = modifier
                        .padding(top = 2.dp)
                ) {
                    Text(
                        text = time,
                        maxLines = 10,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = "|",
                        maxLines = 10,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Medium
                    )
                    Text(
                        text = date,
                        maxLines = 10,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            Card(
                modifier = modifier
                    .width(82.dp)
                    .height(20.dp),
                shape = RoundedCornerShape(20.dp),
                backgroundColor = if (status == "Selesai") Color(0xFF7BBB71) else Color(0xFFF47078),
            ) {
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        6.dp,
                        Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = status,
                        color = Color.White,
                        maxLines = 10,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
        }
    }
}


@Composable
@Preview(showBackground = true)
fun CardOrderFinishPreview() {
    TrashureTheme {
        CardOrderFinish(
            "ID121212131",
            "17.40",
            "26 May 2023",
            "Selesai",
        )
    }
}
