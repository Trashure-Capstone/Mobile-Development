package com.example.trashure.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.trashure.R
import com.example.trashure.ui.components.MyTopBar
import com.example.trashure.ui.components.scanpage.CardInformationViews
import com.example.trashure.ui.screen.scan.ButtonScanSell
import com.example.trashure.ui.screen.scan.ImageScanViews
import com.example.trashure.ui.screen.scan.LogoType
import com.example.trashure.ui.theme.ErrorColor
import com.example.trashure.ui.theme.PrimaryBackgroundColor
import com.example.trashure.ui.theme.PrimaryColor
import com.example.trashure.ui.theme.PrimaryTextColor
import com.example.trashure.ui.theme.Shapes_Larger
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun EditProfileScreen(
    navController: NavHostController = rememberNavController(),
    modifier: Modifier = Modifier
){
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    Scaffold (
        topBar = {
            MyTopBar(title = stringResource(R.string.edit_profile))
        },
        modifier = modifier
    ){innerPadding ->
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.CenterVertically),
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(innerPadding)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.height(30.dp))
            Box{
                Image(
                painter = painterResource(R.drawable.avatarss),
                contentDescription = "Avatar",
                modifier = Modifier
                    .size(110.dp)
                    .clip(CircleShape)
                )
                Icon(
                    imageVector = Icons.Default.Edit,
                    contentDescription = null,
                    tint = Color.Green,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .clickable { }
                )
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(
                text = "Nama",
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 56.dp)
            )
            OutLineTextFieldSample()
            Text(
                text = "Email",
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 56.dp)
            )
            OutLineTextFieldSample()
            Text(
                text = "Nomor Telepon",
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 56.dp)
            )
            OutLineTextFieldSample()
            Spacer(modifier = Modifier.height(30.dp))
            ButtonSave()
            Spacer(modifier = Modifier.height(30.dp))
        }

    }
}

@Preview(showBackground = true)
@Composable
fun EditProfileScreenPreview(){
    TrashureTheme {
        EditProfileScreen()
    }
}

@Composable
fun OutLineTextFieldSample() {
    OutlinedTextField(
        value = "text",
        label = { Text(text = "Enter Your Name") },
        onValueChange = {},
        shape = Shapes_Larger.medium,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = PrimaryColor,
            focusedLabelColor = PrimaryTextColor,
            cursorColor = PrimaryColor,
            backgroundColor = PrimaryBackgroundColor,
            errorBorderColor = ErrorColor
        ),
        singleLine = true,
        maxLines = 1,
    )
}

@Preview
@Composable
fun OutLineTextPreview()
{
    TrashureTheme {
        OutLineTextFieldSample()
    }
}

@Composable
fun ButtonSave(
    modifier: Modifier = Modifier
){
    Button(
        onClick = {},
        shape = Shapes_Larger.small,
        colors = ButtonDefaults.buttonColors(PrimaryColor),
        modifier = modifier
            .width(140.dp)
    ) {
        Text(text = "Simpan")
    }
}