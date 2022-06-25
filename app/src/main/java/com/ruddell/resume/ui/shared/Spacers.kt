package com.ruddell.resume.ui.shared

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun RowScope.RowSpacer() {
    Spacer(Modifier.fillMaxWidth().weight(1f))
}

@Composable
fun ColumnScope.ColumnSpacer() {
    Spacer(Modifier.fillMaxHeight().weight(1f))
}