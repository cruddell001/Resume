package com.ruddell.resume.ui.shared

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp
import com.ruddell.resume.extensions.titleColor

@Composable
fun TitleView(text: String, modifier: Modifier = Modifier, style: TextStyle = TextStyle()) {
    Text(text, modifier = modifier, style = style.merge(TextStyle(titleColor, fontSize = 18.sp)))
}
