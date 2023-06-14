package com.example.trashure.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun MyTopBar(
    navigateBack:()->Unit,
    title : String
) {
    TopAppBar(
        navigationIcon = {
            IconButton(
                onClick = {
                    navigateBack()
                }
            ) {
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
            {},"Identifikasi Jenis Sampah"
        )
    }
}