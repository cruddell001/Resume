package com.ruddell.resume.ui.shared

import androidx.compose.animation.*
import androidx.compose.foundation.LocalIndication
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.TransformOrigin
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ColorExplosion(color: Color, visible: Boolean, offset: Offset, onFinished: () -> Unit) {
    val density = LocalDensity.current
    val config = LocalConfiguration.current
    val maxWidth = (config.screenWidthDp * 2).dp
    val halfWidth = (config.screenWidthDp).dp
    val interactionSource = remember { MutableInteractionSource() }

    LaunchedEffect(key1 = visible, key2 = offset) {
        interactionSource.emit(PressInteraction.Press(offset))
    }
    Box(
        Modifier.size(width = if (visible) 10000.dp else 0.dp, height = if (visible) 5000.dp else 0.dp)
            .indication(interactionSource, LocalIndication.current)
            .background(color = color, shape = RoundedCornerShape(20.dp))
    )
}

@Preview
@Composable
private fun ColorExplosionPreview() {
    val isVisible = remember { mutableStateOf(false) }
    val offset = remember { mutableStateOf(Offset(0f, 0f)) }
    Box(Modifier.fillMaxSize().background(Color.Red).pointerInput(Unit) {
        detectTapGestures {
            offset.value = it
            isVisible.value = !isVisible.value
        }
    }) {
        ColorExplosion(color = Color.Green, visible = isVisible.value, offset = offset.value) {

        }
    }
}
