package com.example.trashure.ui.screen.marketplace

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashure.R
import com.example.trashure.model.ItemsMarket
import com.example.trashure.model.MenuSections
import com.example.trashure.model.dummyItemsMarket
import com.example.trashure.ui.components.marketplace.MenuItemsView
import com.example.trashure.ui.components.MenuSectionsView
import com.example.trashure.ui.components.marketplace.CardMenuMarket
import com.example.trashure.ui.theme.TrashureTheme
import kotlinx.coroutines.launch

@Composable
fun UserMarketScreen(
    menuSections: List<MenuSections>,
    modifier: Modifier = Modifier,
) {
    val scope = rememberCoroutineScope()

    val sectionsListState = rememberLazyListState()
    val itemsListState = rememberLazyListState()
    var selectedSectionIndex by remember { mutableStateOf(0) }

    Scaffold(
        topBar = {
            TopBarUMKM(title = stringResource(R.string.umkm_market))
        },
        modifier = modifier
    ) { innerPadding ->
        Column(modifier.padding(innerPadding)) {
            MenuSectionsView(
                selectedIndex = selectedSectionIndex,
                menuSections = menuSections,
                sectionsListState = sectionsListState,
                onClick = { sectionIndex ->
                    selectedSectionIndex = sectionIndex

                    scope.launch {
                        sectionsListState.animateScrollToItem(sectionIndex)
                        itemsListState.animateScrollToItem(sectionIndex)
                    }
                },
                modifier = modifier
                    .padding(start = 20.dp, top = 20.dp)
            )

            ItemsUserMarketRow(
                dummyItemsMarket,
                modifier.padding(top = 16.dp)
            )

            MenuItemsView(
                menuSections = menuSections,
                itemsListState = itemsListState,
                onPostScroll = {
                    val currentSectionIndex = itemsListState.firstVisibleItemIndex
                    if (selectedSectionIndex != currentSectionIndex) {
                        selectedSectionIndex = currentSectionIndex

                        scope.launch {
                            sectionsListState.animateScrollToItem(currentSectionIndex)
                        }
                    }
                }
            )
        }
    }
}

@Composable
fun TopBarUserMarket(
    title : String,
    modifier : Modifier = Modifier,
) {
    TopAppBar(
        navigationIcon = {
            IconButton(onClick = {}) {
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
        },
        actions = {
            IconButton(onClick = {}) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color(0xFF1A395A)
                )
            }
        }
    )
}


@Composable
fun ItemsUserMarketRow(
    listItemMarket : List<ItemsMarket>,
    modifier: Modifier = Modifier,
){
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(horizontal = 30.dp),
        modifier = modifier
            .padding(top = 10.dp)
    ) {
        items(listItemMarket) { items ->
            CardMenuMarket(
                image = items.image ,
                title = items.title ,
                price = items.price )
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TopBarUserMarketPreview(){
    TrashureTheme {
        TopBarUserMarket(
            "Toko Sampah"
        )
    }
}