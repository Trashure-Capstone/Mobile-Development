package com.example.trashure.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashure.R
import androidx.compose.material.Text
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
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
import com.example.trashure.ui.components.homepage.CardActivityViews
import com.example.trashure.ui.components.homepage.CardCategoryViews
import com.example.trashure.ui.components.homepage.CardMenuViews
import com.example.trashure.ui.components.homepage.CardNewsViews
import com.example.trashure.ui.components.homepage.HomeSection
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
){
    HomeScreenContent(modifier)
}

@Composable
fun HomeScreenContent(
    modifier: Modifier = Modifier,
){
    Box(
        modifier = Modifier
            .height(192.dp)
            .fillMaxWidth()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        PrimaryBackgroundColor, Color(0xFFCCEFD9)
                    )
                )
            )
    )
    Column(
        modifier = Modifier
            .fillMaxSize()
    ){
        Spacer(modifier = Modifier.height(80.dp))
        Box {
            BannerHome("Hilalhmdy")

        }
        Spacer(modifier = Modifier.height(30.dp))
        Category(
            modifier = modifier
                .fillMaxWidth()
        )
        Column(
            modifier = modifier
                .padding(top = 10.dp)
        ) {
            MenuCategory(dummyMenu)
        }
        MenuCategory(dummyMenuToko)
        HomeSection(
            title = stringResource(R.string.news_title),
            content = { NewsCategory(dummyNews)},
            modifier = modifier
                .padding(top = 10.dp)
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
        HomeScreenContent()
    }
}

@Composable
fun BannerHome(
    username: String,
    modifier: Modifier = Modifier,
) {
    Box(modifier = modifier
        .fillMaxWidth()
    ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .align(Alignment.Center)
            ) {
                Row {
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
            .fillMaxWidth()
            .padding(top = 16.dp)
    ) {
        items(listMenu){data ->
            CardMenuViews(
                icon = data.icon,
                title = data.title,
                description = data.description,
                color = data.color
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
        contentPadding = PaddingValues(horizontal = 26.dp),
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
        contentPadding = PaddingValues(horizontal = 26.dp),
        modifier = modifier
            .padding(top = 10.dp)
    ) {
        items(listActivity) { activity ->
            CardActivityViews(
                id = activity.id,
                image = activity.image
            )
        }
    }
}

@Composable
fun Category(
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        CardCategoryViews(
            icon = R.drawable.money,
            title = stringResource(R.string.saldo),
            input = 30000
        )
        CardCategoryViews(
            icon = R.drawable.coin,
            title = stringResource(R.string.poin),
            input = 3000
        )
    }
}



