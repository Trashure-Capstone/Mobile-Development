package com.example.trashure.ui.screen.home

import android.app.Activity
import android.view.MenuItem
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashure.R
import androidx.compose.material.Text
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.sp
import com.example.trashure.model.ActivityNews
import com.example.trashure.model.Menu
import com.example.trashure.model.News
import com.example.trashure.model.dummyActivity
import com.example.trashure.model.dummyMenu
import com.example.trashure.model.dummyMenuToko
import com.example.trashure.model.dummyNews
import com.example.trashure.ui.components.CardActivityViews
import com.example.trashure.ui.components.CardMenuTokoViews
import com.example.trashure.ui.components.CardMenuViews
import com.example.trashure.ui.components.CardNewsViews
import com.example.trashure.ui.components.HomeSection
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier,
){
    Column(
    ){
        Box {
            BannerHome("Hilalhmdy")

        }
        MenuCategory(dummyMenu)
        MenuTokoCategory(dummyMenuToko)
        HomeSection(
            title = stringResource(R.string.news_title),
            content = { NewsCategory(dummyNews)}
        )
        HomeSection(
            title = stringResource(R.string.activity_title),
            content = { ActivityCategory(dummyActivity)},
            modifier = modifier
                .padding(top = 10.dp)
        )
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    TrashureTheme {
        HomeScreen()
    }
}

@Composable
fun BannerHome(
    username: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier) {
        Image(
            painter = painterResource(R.drawable.decoration),
            contentDescription = "Banner Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier.height(192.dp)
        )
        Column(verticalArrangement = Arrangement.spacedBy(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .align(Alignment.Center)
        ) {
            Row{
                Text(
                    text = "Hello, $username",
                    maxLines = 1,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(end = 8.dp)
                )
                Image(
                    painter = painterResource(R.drawable.wavinghand),
                    contentDescription = "null",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
            Text(
                text = "Sudah kamu membuang sampah?",
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TextPreview() {
    TrashureTheme {
        BannerHome("Hilalhmdy")
    }
}

@Composable
fun MenuCategory(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 50.dp)
    ) {
        items(listMenu){data ->
            CardMenuViews(
                icon = data.icon,
                title = data.title,
                description = data.description
            )
        }
    }
}

@Composable
fun MenuTokoCategory(
    listMenu: List<Menu>,
    modifier: Modifier = Modifier,
){
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        items(listMenu){data ->
            CardMenuTokoViews(
                icon = data.icon,
                title = data.title,
                description = data.description
            )
        }
    }
}

@Composable
fun NewsCategory(
    listNews: List<News>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .padding(top = 10.dp)
    ) {
        items(listNews) { news ->
            CardNewsViews(
                image = news.image,
                description = news.description
            )
        }
    }
}


@Composable
fun ActivityCategory(
    listActivity: List<ActivityNews>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 16.dp),
        modifier = modifier
            .padding(top = 10.dp)
    ) {
        items(listActivity) { news ->
            CardActivityViews(
                image = news.image,
                title = ""
            )
        }
    }
}



