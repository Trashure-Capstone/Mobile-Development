package com.example.trashure.ui.components.sellpage

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.trashure.ui.components.homepage.SectionText

@Composable
fun CategorySection(
    title: String,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    Column(modifier) {
        CategoryText(title, modifier)
        content()
    }
}