package com.example.trashure.ui.screen.marketplace

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.components.marketplace.CardMenuMarketPlace
import com.example.trashure.ui.theme.TrashureTheme


@Composable
fun MarketPlaceScreen(
    navigateBack: () -> Unit,
    navigateToUserMarket: () -> Unit,
    navigateToUMKMMarket: () -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            TopBarMarketChoose(
                navigateBack= navigateBack,
                title = "Toko Sampah"
            )
        }
    ) {innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Text(
                text = stringResource(id = R.string.choose_market),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF1A395A),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(top = 120.dp)
            )
            Spacer(modifier = Modifier.height(26.dp))
            ShowMarketMenu(
                modifier = modifier.fillMaxWidth(),
                navigateToUMKMMarket = navigateToUMKMMarket,
                navigateToUserMarket = navigateToUserMarket
            )
        }

    }
}

@Composable
@Preview
fun MarketPlaceScreenPreview(){
    TrashureTheme {
        MarketPlaceScreen(
            {},{},{}
        )
    }
}


@Composable
fun TopBarMarketChoose(
    navigateBack: () -> Unit,
    title : String,
    modifier : Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {
                navigateBack()
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Menu",
                    tint = Color(0xFF1A395A)
                )
            }
        },
        backgroundColor = Color.White,
        title = {
            Text(
                text = title,
                color = Color(0xFF1A395A),
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(start = 60.dp)
            )
        }
    )
}

@Composable
@Preview(showBackground = true)
fun TopBarMarketPreview(){
    TrashureTheme {
        TopBarMarketChoose(
            {},
            "Toko Sampah"
        )
    }
}

@Composable
fun ShowMarketMenu(
    navigateToUserMarket: () -> Unit,
    navigateToUMKMMarket: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
            .padding(top = 10.dp)
    ) {
        CardMenuMarketPlace(
            icon = R.drawable.shopping_basket,
            title = "Trashure Market",
            navigateMarket = navigateToUserMarket

        )
        CardMenuMarketPlace(
            icon = R.drawable.store,
            title = "UMKM Market",
            navigateMarket = navigateToUMKMMarket
        )
    }
}

