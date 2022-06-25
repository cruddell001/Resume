package com.ruddell.resume.ui.shared

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.ruddell.resume.extensions.cardBackgroundColor

@Composable
fun ThemedCard(modifier: Modifier = Modifier, onClick: () -> Unit = {}, content: @Composable () -> Unit) {
    Card(
        backgroundColor = cardBackgroundColor,
        modifier = modifier.padding(8.dp).clickable(onClick = onClick)
    ) {
        Box(Modifier.padding(8.dp)) {
            content()
        }
    }
}