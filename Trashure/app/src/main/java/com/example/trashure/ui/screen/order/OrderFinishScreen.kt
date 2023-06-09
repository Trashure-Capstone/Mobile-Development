package com.example.trashure.ui.screen.order

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashure.model.OrderFinish
import com.example.trashure.model.dummyOrderFinish
import com.example.trashure.ui.components.orderpage.CardOrderFinish

@Composable
fun OrderFinishScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        ListOrderFinish(dummyOrderFinish)
    }
}

@Preview(showBackground = true)
@Composable
fun MoviesScreenPreview() {
    OrderFinishScreen()
}

@Composable
fun ListOrderFinish(
    listOrderFinish: List<OrderFinish>,
    modifier : Modifier = Modifier,
){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxWidth()
            .padding(top = 16.dp)
    ){
        items(listOrderFinish){data ->
            CardOrderFinish(
                id = data.title,
                time = data.time,
                date = data.date,
                status =data.status,
            )
        }

    }
}
