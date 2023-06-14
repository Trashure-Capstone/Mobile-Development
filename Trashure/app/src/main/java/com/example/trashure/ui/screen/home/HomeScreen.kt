package com.example.trashure.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashure.R
import androidx.compose.material.Text
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trashure.di.Injection
import com.example.trashure.model.*
import com.example.trashure.ui.common.UiState
import com.example.trashure.ui.components.homepage.*
import com.example.trashure.ui.screen.news.DetailNewsContent
import com.example.trashure.ui.theme.Blue_1
import com.example.trashure.ui.theme.Green_1
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.TrashureTheme
import com.example.trashure.utils.ViewModelFactory

@Composable
fun HomeScreen(
    navigateToMarketPlace : () -> Unit,
    navigateToSellPage : () -> Unit,
    navigateToDetail: (Long) -> Unit,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository(context = LocalContext.current))
    )
){
    viewModel.newsState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getAllNews()
            }
            is UiState.Success -> {
                HomeScreenContent(
                    navigateToMarketPlace = navigateToMarketPlace,
                    navigateToSellPage = navigateToSellPage,
                    navigateToDetail = navigateToDetail
                )
            }
            is UiState.Error -> {}
            else -> {

            }
        }

    }

}


@Composable
fun HomeScreenContent(
    navigateToMarketPlace : () -> Unit,
    navigateToSellPage: () -> Unit,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        Box(
            contentAlignment = Alignment.Center,
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
        ){
            BannerHome("Hilalhmdy")
        }
        Category(
            modifier = modifier
                .fillMaxWidth()
                .offset(y = (-30).dp)
        )
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            CardMenuViews(
                navigate = navigateToSellPage,
                icon = R.drawable.recycling,
                title = "Jual Sampah",
                description = "Bersihkan lingkunganmu sekarang",
                color = Green_1
            )
        }
        Spacer(modifier = Modifier.height(15.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
        ){
            CardMenuViews(
                navigate = navigateToMarketPlace,
                icon = R.drawable.shopping,
                title = "Toko Sampah",
                description = "Dapatkan sampah untuk keperluan bisnis",
                color = Blue_1
            )
        }
        
        HomeSection(
            title = stringResource(R.string.news_title),
            content = { NewsCategory(dummyNews, navigateToDetail)},
            modifier = modifier
                .padding(top = 10.dp)
        )
        HomeSection(
            title = stringResource(R.string.activity_title),
            content = { ActivityCategory(dummyActivity)},
            modifier = modifier
                .padding(top = 10.dp)
        )
        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun HomeScreenPreview() {
    TrashureTheme {
        HomeScreenContent({}, {}, {})
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
fun NewsCategory(
    listNews: List<News>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,

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
                title = news.title,
                modifier = Modifier
                    .clickable {
                        navigateToDetail(news.id)
                    }
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



