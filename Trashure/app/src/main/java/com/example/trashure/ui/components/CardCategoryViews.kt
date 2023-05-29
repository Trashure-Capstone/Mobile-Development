package com.example.trashure.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CardCategoryViews(
    icon: Int,
    title: String,
    input: String,
    modifier: Modifier = Modifier,
) {
    Card(
        modifier = modifier.width(142.dp),
        shape = RoundedCornerShape(10.dp)
    ){
        Row(
            modifier = modifier
                .width(80.dp)
                .padding(start = 12.dp, top = 12.dp, bottom = 12.dp)
        ){
            Image(
                painter = painterResource(icon), 
                contentDescription = null,
                modifier = Modifier
                    .padding(top = 2.dp)
                    .size(20.dp)
            )
            Column( modifier = Modifier
                .padding(start= 11.dp)
                .weight(1.0f)
            ) {
                Text(
                    text = title,
                    maxLines = 1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = input,
                    maxLines = 1,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }
        }
    }

}

@Composable
@Preview(showBackground = true)
fun CardCategoryPreview() {
    TrashureTheme {
        CardCategoryViews(
            R.drawable.money,
            "Saldo Anda",
            "Rp 30.000"
        )
    }
}
