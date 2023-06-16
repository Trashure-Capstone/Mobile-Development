package com.example.trashure.ui.components.sellpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun ModalDetailSelling(
    price : Int,
    sellTrash: ()->Unit,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(10.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 12.dp, top = 12.dp, bottom = 12.dp, end = 12.dp)
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(
                6.dp,
                Alignment.CenterHorizontally
            ),
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.vector_recycle),
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(20.dp)
            )
            Column(
                modifier = Modifier
                    .padding(start = 6.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.order_detail),
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }

        Divider(color = Color(0xFFD8D8D8), thickness = 1.dp)

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
                    text = stringResource(id = R.string.bonus_coin),
                    maxLines = 1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = (0.1*price).toInt().toString(),
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
                    text = stringResource(id = R.string.total_price),
                    maxLines = 1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = price.toString(),
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
                    text = stringResource(id = R.string.admin_fee),
                    maxLines = 1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = (0.2*price).toInt().toString(),
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
        }

        Divider(color = Color(0xFF1A395A),thickness = 1.dp)

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
                    text = stringResource(id = R.string.estimated_earning),
                    maxLines = 1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
            }
            Text(
                text = (0.8*price).toInt().toString(),
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )
            
        }
        Spacer(modifier = Modifier.height(30.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            Button(
                onClick = {
                    sellTrash()
                },
                shape = Shapes_Larger.small,
                colors = ButtonDefaults.buttonColors(PrimaryColor),
                modifier = modifier
                    .width(140.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.confirm_sell_trash),
                    textAlign = TextAlign.Center
                )
            }
        }
        
    }
}

@Preview( showBackground = true)
@Composable
fun ModalDetailPreview(){
    TrashureTheme {
        ModalDetailSelling(price = 3000, {})
    }
}