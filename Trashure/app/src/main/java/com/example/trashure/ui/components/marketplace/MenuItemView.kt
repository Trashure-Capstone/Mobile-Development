package com.example.trashure.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.model.MenuSections

@Composable
fun MenuItemView(
    section: MenuSections,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
    ) {
        section.menuItems.forEach { menuItem ->
            Card (
                modifier = modifier
                    .width(340.dp)
                    .wrapContentHeight(),
                shape = RoundedCornerShape(10.dp)
            ){
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = modifier
                        .padding(start = 20.dp, top = 20.dp, bottom = 20.dp, end = 20.dp)
                ) {
                    Column(
                        modifier = Modifier.weight(1f),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Text(
                            text = menuItem.title,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            ),
                            color = Color.DarkGray
                        )
                        Text(
                            text = menuItem.price,
                            style = TextStyle(
                                fontSize = 20.sp,
                                fontWeight = FontWeight.SemiBold
                            ),
                            color = Color.DarkGray
                        )
                    }
                    Image(
                        modifier = Modifier.size(100.dp),
                        painter = painterResource(id = menuItem.imageID),
                        contentScale = ContentScale.FillWidth,
                        contentDescription = "Menu Item Image"
                    )
                }
            }
        }
    }
}