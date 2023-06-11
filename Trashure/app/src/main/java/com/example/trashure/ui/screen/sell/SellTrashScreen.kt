package com.example.trashure.ui.screen.sell

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.trashure.R
import com.example.trashure.ui.components.MyTopBar
import com.example.trashure.ui.components.scanpage.CardInformationViews
import com.example.trashure.ui.components.sellpage.CategoryItem
import com.example.trashure.ui.components.sellpage.CategoryPhoto
import com.example.trashure.ui.components.sellpage.CategorySection
import com.example.trashure.ui.components.sellpage.CategoryWeight
import com.example.trashure.ui.components.sellpage.ModalBottomSheet
import com.example.trashure.ui.screen.scan.ButtonScanSell
import com.example.trashure.ui.screen.scan.ImageScanViews
import com.example.trashure.ui.screen.scan.LogoType
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.ui.theme.TrashureTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun SellTrashScreen(
    modifier: Modifier = Modifier
) {
    val modalBottomSheetState = rememberModalBottomSheetState(initialValue = ModalBottomSheetValue.Hidden)
    val scope = rememberCoroutineScope()
    ModalBottomSheetLayout(
        sheetContent = {
            ModalBottomSheet()
        },
        sheetState = modalBottomSheetState,
        sheetShape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp),
        sheetBackgroundColor = Color.White,
        // scrimColor = Color.Red,  // Color for the fade background when you open/close the drawer
    ){
    Scaffold (
        topBar = {
            SellTrashTopBar(title = stringResource(R.string.sell_page))
        },
        modifier = modifier
    ){ innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(20.dp))
            CategorySection(
                title = "Jenis Sampah",
                content = {
                    CategoryItem(
                        icon = R.drawable.trashsell,
                        title = "Pilih jenis sampah",
                        modifier = modifier
                            .clickable {
                                scope.launch {
                                    modalBottomSheetState.show()
                                }
                            }
                    )
                }
            )
            CategorySection(
                title = "Berat Sampah",
                content = {
                    CategoryWeight(
                        rewardId = 2,
                        count = 0,
                        onProductCountChanged = { rewardId, count ->}
                    )
                }
            )
            CategorySection(
                title = "Foto Sampah",
                content = {CategoryPhoto()}
            )
            CategorySection(
                title = "Lokasi Pengambilan",
                content = {
                          CategoryItem(
                              icon = R.drawable.location,
                              title = "Tentukan lokasimu",
                          )
                }
            )
            CategorySection(
                title = "Jadwal Pengambilan",
                content = {
                    CategoryItem(
                        icon = R.drawable.calendardatefill,
                        title = "Tentukan tanggal",
                    )
                }
            )
            CategoryItem(
                icon = R.drawable.clockfill,
                title = "Tentukan waktu"
            )
            Spacer(modifier = Modifier.height(100.dp))
            Button(
                onClick = {},
                shape = Shapes_Larger.small,
                colors = ButtonDefaults.buttonColors(PrimaryColor),
                modifier = modifier
                    .width(140.dp)
                    .padding(bottom = 20.dp)
            ) {
                Text(text = "Jual Sampah")
            }

        }
    }
    }

}



@Composable
fun SellTrashTopBar(
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
        title = {
            Text(text = title, color = Color(0xFF1A395A))
        }
    )
}

@Preview
@Composable
fun SellTrashPreview(){
    TrashureTheme {
        SellTrashScreen()
    }
}