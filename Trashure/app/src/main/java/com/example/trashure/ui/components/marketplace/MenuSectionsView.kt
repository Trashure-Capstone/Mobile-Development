package com.example.trashure.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.trashure.model.MenuSections

@Composable
fun MenuSectionsView(
    selectedIndex: Int,
    menuSections: List<MenuSections>,
    sectionsListState: LazyListState,
    modifier: Modifier = Modifier,
    onClick: (sectionIndex: Int) -> Unit
) {
    LazyRow(
        modifier = modifier
            .padding(top = 10.dp),
        state = sectionsListState
    ) {
        menuSections.forEachIndexed { i, section ->
            item {
                SectionTextView(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .clickable { onClick(i) },
                    text = section.title,
                    isSelected = selectedIndex == i
                )
            }
        }
    }
}