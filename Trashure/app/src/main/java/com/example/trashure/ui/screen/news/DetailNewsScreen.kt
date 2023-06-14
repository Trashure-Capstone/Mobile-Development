package com.example.trashure.ui.screen.news

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.components.MyTopBar
import com.example.trashure.ui.theme.TrashureTheme

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.trashure.di.Injection
import com.example.trashure.ui.common.UiState
import com.example.trashure.utils.ViewModelFactory

@Composable
fun DetailNewsScreen(
    id : Long,
    viewModel: DetailNewsViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository(context = LocalContext.current)
        )
    ),
    navigateBack: () -> Unit
){
    viewModel.newsState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when(uiState){
            is UiState.Loading -> {
                viewModel.getNewsById(id)
            }
            is UiState.Success -> {
                val data = uiState.data
                DetailNewsContent(
                    image = data.image,
                    title = data.title,
                    description = data.description,
                    date = data.date,
                    navigateBack = navigateBack)
            }
            is UiState.Error -> {}
            else -> {

            }
        }

    }
}


@Composable
fun DetailNewsContent (
    image : Int,
    title : String,
    description: String,
    date : String,
    navigateBack:() -> Unit,
    modifier: Modifier = Modifier
){
    Scaffold(
        topBar = {
            MyTopBar(
                navigateBack = navigateBack,
                title = stringResource(R.string.news_update)
            )
        },
        modifier = modifier

    ) { innerPadding->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(30.dp))

            Text(
                text = title,
                maxLines = 3,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = modifier
                    .padding(horizontal = 30.dp)
            )

            Text(
                text = date,
                maxLines = 1,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(10.dp))

            Card (
                modifier = modifier
                    .width(300.dp)
                    .height(160.dp),
                shape = RoundedCornerShape(18.dp),
                border = BorderStroke(width = 2.dp, color = Color.Black)
            ){
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight()
                )
            }

            Text(
                text = description,
                maxLines = 100,
                fontSize = 12.sp,
                fontWeight = FontWeight.Medium,
                modifier = modifier
                    .padding(horizontal = 16.dp)

            )
        }

    }
}


@Preview(showBackground = true)
@Composable
fun DetailNewsPreview(){
    TrashureTheme {
        DetailNewsContent(
            image = R.drawable.news_05,
            title = "Pabrik Daur Ulang Botol di Kendal Jawa Tengah Turut Kurangi Dampak Limbah Plastik",
            description = "Polusi yang diakibatkan limbah plastik merupakan masalah pelik bagi semua negara. Setiap tahun, sekitar 8 hingga 12 juta ton plastik berakhir di lautan.  \n" +
                    "\n" +
                    "Menurut data statistik persampahan domestik Indonesia, produksi sampah plastik di dalam negeri mencapai 5.4 juta ton per tahun atau 14 persen dari total produksi sampah. \n" +
                    "\n" +
                    "Untuk menjawab persoalan limbah plastik itu, diperlukan kolaborasi dari berbagai pemangku kepentingan dengan menggunakan pendekatan ekonomi sirkuler.  \n" +
                    "\n" +
                    "Salah satu kolaborasi terbaru untuk menjawab persoalan limbah plastik di Indonesia adalah pembangunan pabrik daur ulang botol PET di Kawasan Industri Kendal, Jawa Tengah. ",
            date = "26 Mei 2023",
            navigateBack = { /*TODO*/ })
    }
}


