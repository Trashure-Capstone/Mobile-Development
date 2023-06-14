package com.example.trashure.ui.screen.profile.changepassword

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.R
import com.example.trashure.ui.components.MyTopBar
import com.example.trashure.ui.screen.profile.editprofile.ButtonSave
import com.example.trashure.ui.screen.profile.editprofile.OutLineTextFieldSample
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun ChangePasswordScreen(
    navigateBack:()->Unit,
    modifier: Modifier = Modifier
){
    Scaffold (
        topBar = {
            MyTopBar(
                navigateBack = navigateBack,
                title = stringResource(R.string.change_password)
            )
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
            Text(
                text = "Password Baru",
                maxLines = 1,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 56.dp)
            )
            OutLineTextFieldSample()
            Text(
                text = "Confirm New Password",
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
        }

    }
}

@Preview(showBackground = true)
@Composable
fun ChangePasswordScreenPreview(){
    TrashureTheme {
        ChangePasswordScreen(
            {}
        )
    }
}