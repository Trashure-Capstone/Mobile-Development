package com.example.trashure.ui.components.sellpage

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.trashure.ui.components.homepage.SectionText
import com.example.trashure.ui.theme.TrashureTheme

@Composable
fun CategoryText(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        color = Color(0xFF1A395A),
        fontSize = 14.sp,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
            .padding(vertical = 8.dp)
    )
}

@Composable
@Preview(showBackground = true)
fun TextPreview() {
    TrashureTheme {
        CategoryText(
            "Activity"
        )
    }
}
