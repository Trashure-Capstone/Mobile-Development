package com.example.trashure

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.trashure.ui.screen.order.OrderScreen
import com.example.trashure.ui.screen.order.TabItem
import com.example.trashure.ui.screen.order.TabsContent
import com.example.trashure.ui.theme.TrashureTheme
import com.google.accompanist.pager.rememberPagerState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TrashureTheme {
                TrashureApp()
            }
        }
    }
}