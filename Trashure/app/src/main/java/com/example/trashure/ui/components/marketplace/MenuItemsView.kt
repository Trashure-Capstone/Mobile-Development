package com.example.trashure.ui.components.marketplace

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.model.MenuSections

@Composable
fun MenuItemsView(
    menuSections: List<MenuSections>,
    itemsListState: LazyListState,
    onPostScroll: () -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        state = itemsListState,
        modifier = Modifier
            .padding()
            .nestedScroll(object : NestedScrollConnection {
                override fun onPostScroll(
                    consumed: Offset,
                    available: Offset,
                    source: NestedScrollSource
                ): Offset {
                    onPostScroll()
                    return super.onPostScroll(consumed, available, source)
                }
            })
    ) {
        menuSections.forEach { section ->
            item {
                Text(
                    modifier = Modifier.padding(32.dp),
                    text = section.title,
                    style = TextStyle(fontSize = 24.sp, fontWeight = FontWeight.Bold),
                    color = Color(0xFF1A395A)
                )
                MenuItemView(
                    section
                )
            }
        }
    }
}