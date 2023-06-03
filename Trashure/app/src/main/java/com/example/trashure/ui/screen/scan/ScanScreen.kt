package com.example.trashure.ui.screen.scan

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trashure.R
import com.example.trashure.ui.components.MyTopBar
import com.example.trashure.ui.components.scanpage.CardInformationViews
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun ScanScreenContent (
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold (
        topBar = {
            MyTopBar(title = stringResource(R.string.scan_page))
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
            Spacer(modifier = Modifier.height(30.dp))
            ImageScanViews(R.drawable.avatarss)
            LogoType(R.drawable.hdpe)
            Spacer(modifier = Modifier.height(4.dp))
            CardInformationViews(title = "Tipe Plastik", description = "PETE")
            CardInformationViews(title = "Nama Lain", description = "Polyethylene Terephthalate")
            CardInformationViews(title = "Manfaat", description = "memiliki kemampuan untuk mencegah oksigen masuk dan merusak produk didalamnya.")
            CardInformationViews(title = "Kekurangan", description = "Mengandung antimon trioksida (yang dianggap sebagai karsinogen) yang mampu menyebabkan kanker mampu menyebabkan kanke mampu menyebabkan kanke ")
            ButtonScanSell(
                modifier = modifier
                    .fillMaxWidth()
            )

        }
    }

}

@Preview
@Composable
fun ScanScreenPreview(){
    TrashureTheme {
        ScanScreenContent()
    }
}


@Composable
fun MyTopBar(
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
fun MyTopBarPreview(){
    TrashureTheme {
        MyTopBar(
            "Identifikasi Jenis Sampah"
        )
    }
}

@Composable
fun ImageScanViews(
    image: Int,
    modifier: Modifier = Modifier,
) {
    Card (
        modifier = modifier
            .width(220.dp)
            .height(220.dp),
        shape = RoundedCornerShape(18.dp),
        border = BorderStroke(width = 2.dp, color = Color.Black)
    ){
        Column {
            Image(
                painter = painterResource(image),
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )
        }
    }
}

@Preview
@Composable
fun ImageScanPreview(){
    TrashureTheme {
        ImageScanViews(R.drawable.avatarss )
    }
}

@Composable
fun LogoType(
    image: Int,
    modifier: Modifier = Modifier
){
    Column (
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Text(
            text = stringResource(R.string.logo_title),
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold,
        )
        Image(
            painter = painterResource(image) ,
            contentDescription = null,
            modifier = modifier
                .size(50.dp)
        )
    }
}

@Preview
@Composable
fun LogoTypePreview(){
    TrashureTheme {
        LogoType(R.drawable.hdpe)
    }
}

@Composable
fun ButtonChoose(
    buttonName: String,
    modifier: Modifier = Modifier
){
    Button(
        onClick = {},
        shape = Shapes_Larger.small,
        colors = ButtonDefaults.buttonColors(PrimaryColor),
        modifier = modifier
            .width(140.dp)
    ) {
        Text(text = buttonName)
    }
}

@Composable
fun ButtonScanSell(
    modifier: Modifier = Modifier
){
    Row(
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        ButtonChoose(buttonName = "Scan Sampah")
        ButtonChoose(buttonName = "Jual SAMPAH")
    }
}


@Preview
@Composable
fun ButtonChoosePreview(){
    TrashureTheme {
        ButtonChoose(buttonName = stringResource(R.string.scan_trash))
    }
}
