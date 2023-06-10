package com.example.trashure.ui.components.orderpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.trashure.R
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.Shapes
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun DialogOrderFinish(
    id : String,
    time : String,
    date : String,
    type : String,
    coin : String,
    earn : String,
    status : String,
    onCancelClicked: () -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = onCancelClicked) {
        Box(
            modifier = Modifier
                .clip(shape = Shapes.medium)
                .background(PrimaryBackgroundColor)
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                modifier = modifier
                    .width(320.dp)
                    .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)

            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                ) {
                    Column( modifier = Modifier
                        .weight(1.0f)
                    ) {
                        Text(
                            text = id,
                            maxLines = 1,
                            fontSize = 14.sp,
                            fontWeight = FontWeight.ExtraBold
                        )
                    }
                    Row(
                        horizontalArrangement = Arrangement.spacedBy(6.dp, Alignment.CenterHorizontally),
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = modifier
                            .background(
                                color = Color(0XFFCCEFD9),
                                shape = RoundedCornerShape(10.dp)
                            )
                            .width(140.dp)
                            .height(22.dp)
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
                Text(
                    text = "Pesanan Telah $status",
                    maxLines = 2,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Text(
                    text = "Orderan : Sampah $type",
                    maxLines = 10,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )

                Divider(color = Color(0xFF1A395A),thickness = 1.dp)

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(R.drawable.coin),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .size(20.dp)
                    )
                    Column( modifier = Modifier
                        .padding(start = 11.dp)
                        .weight(1.0f)
                    ) {
                        Text(
                            text = "Bonus Coin",
                            maxLines = 1,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = coin,
                        maxLines = 1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Row(
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .fillMaxWidth()
                ){
                    Image(
                        painter = painterResource(R.drawable.money),
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .size(20.dp)
                    )
                    Column( modifier = Modifier
                        .padding(start = 11.dp)
                        .weight(1.0f)
                    ) {
                        Text(
                            text = "Total Pendapatan",
                            maxLines = 1,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Medium
                        )
                    }
                    Text(
                        text = earn,
                        maxLines = 1,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium
                    )
                }

                Column(
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 10.dp)
                ) {
                    Card(
                        modifier = modifier
                            .width(82.dp)
                            .height(30.dp)
                            .clickable{ onCancelClicked()},
                        shape = RoundedCornerShape(20.dp),
                        backgroundColor = Color(0xFFF47078),
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.spacedBy(
                                6.dp,
                                Alignment.CenterHorizontally
                            ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Tutup",
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
    }
}


@Composable
@Preview(showBackground = true)
fun DialogOrderFinishPreview(){
    TrashureTheme {
        DialogOrderFinish(
            id = "ID12313122",
            time = "17.50",
            date = "26 May 2023",
            type = "Plastik",
            coin = "100",
            earn = "2.400",
            status = "Selesai",
            onCancelClicked = { /*TODO*/ }
        )
    }
}