package com.example.trashure.ui.components.sellpage

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.trashure.R
import com.example.trashure.model.TrashTypeList
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun ModalBottomSheet(
    modifier: Modifier = Modifier,
    onClick: (String) -> Unit
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
                    text = "Pilih Jenis Sampahmu",
                    maxLines = 1,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    
    
        Divider(color = Color(0xFFD8D8D8),thickness = 1.dp)
        
        for (item in TrashTypeList){
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 6.dp, bottom = 6.dp)
                    .clickable {
                        onClick(item.id)
                    },
            ) {
                Card(
                    modifier = modifier
                        .size(50.dp),
                    shape = RoundedCornerShape(100.dp),
                    backgroundColor = Color(0xFFF47078)
                ) {
                    AsyncImage(
                        model = item.logo,
                        contentDescription = null,
                        modifier = Modifier
                            .padding(top = 2.dp)
                            .size(20.dp)
                    )
                }
                Column(
                    modifier = Modifier
                        .padding(start = 20.dp)
                ) {
                    Text(
                        text = item.name,
                        maxLines = 1,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
    
            Divider(color = Color(0xFFD8D8D8),thickness = 1.dp)
    
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ModalBottomSheetPreview(){
    TrashureTheme {
        ModalBottomSheet(onClick = {})
    }
}