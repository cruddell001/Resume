package com.ruddell.resume.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruddell.resume.R
import com.ruddell.resume.extensions.backgroundColor
import com.ruddell.resume.extensions.iconImage
import com.ruddell.resume.extensions.title
import com.ruddell.resume.ui.shared.RowSpacer
import com.ruddell.resume.ui.shared.TitleView
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun HomeView() {
    val showContent = remember { mutableStateOf(false) }
    LaunchedEffect(key1 = Unit) {
        launch {
            delay(3000L)
            showContent.value = true
        }
    }
    Box(contentAlignment = Alignment.BottomStart) {
        Image(
            painter = painterResource(id = R.drawable.profile),
            contentDescription = stringResource(id = R.string.my_name),
            modifier = Modifier.fillMaxSize().clickable(interactionSource = remember { MutableInteractionSource() }, indication = null) {
                showContent.value = !showContent.value
            },
            contentScale = ContentScale.FillHeight
        )
        Box(Modifier.fillMaxSize().background(brush = Brush.verticalGradient(
            colors = listOf(
                Color.Black.copy(0f),
                Color.Black.copy(1f)
            )
        )))
        Column(Modifier.padding(start = 16.dp, bottom = 16.dp)) {
            TitleView(
                text = stringResource(R.string.my_name_two_lines),
                style = TextStyle(
                    color = Color.White,
                    fontSize = 72.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            TitleView(
                text = stringResource(id = R.string.position),
                style = TextStyle(color = Color.White, fontSize = 24.sp)
            )
        }
        AnimatedVisibility(
            visible = showContent.value,
            enter = slideInVertically(initialOffsetY = { it / 2 }),
            exit = slideOutVertically(targetOffsetY = { it / 2 })
        ) {
            TopLevelContentView {
                // TODO: go to detail screen
                showContent.value = false
            }
        }
    }
}

@Composable
private fun TopLevelContentView(onClick: (Details) -> Unit) {
    Box {
        Column(Modifier.padding(top = 20.dp).background(colorResource(id = R.color.cardBackground))) {
            TitleView(
                text = stringResource(R.string.my_name),
                style = TextStyle(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Center,
                    fontSize = 24.sp
                ),
                modifier = Modifier.fillMaxWidth().padding(top = 24.dp)
            )
            Row(Modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
                ItemButton(detail = Details.WORK_EXPERIENCE) {
                    onClick(Details.WORK_EXPERIENCE)
                }
                ItemButton(detail = Details.EDUCATION) {
                    onClick(Details.EDUCATION)
                }
            }
            Row(Modifier.padding(start = 8.dp, end = 8.dp, bottom = 8.dp)) {
                ItemButton(detail = Details.SKILLS) {
                    onClick(Details.SKILLS)
                }
                ItemButton(detail = Details.ABOUT) {
                    onClick(Details.ABOUT)
                }
            }
        }
        Row(Modifier.fillMaxWidth()) {
            RowSpacer()
            Text(
                text = stringResource(id = R.string.resume),
                modifier = Modifier.background(colorResource(R.color.md_light_blue_400), RoundedCornerShape(8.dp)).padding(8.dp),
                style = TextStyle(Color.White, fontSize = 18.sp)
            )
            RowSpacer()
        }
    }
}

@Composable
private fun RowScope.ItemButton(detail: Details, onClick: () -> Unit) {
    Column(
        Modifier
            .fillMaxWidth()
            .weight(1f)
            .padding(8.dp)
            .background(color = detail.backgroundColor())
            .clickable { onClick() }
    ) {
        Image(painter = detail.iconImage(), contentDescription = detail.name, modifier = Modifier.padding(24.dp))
        TitleView(text = detail.title(), modifier=  Modifier.fillMaxWidth().padding(bottom = 16.dp), style = TextStyle(textAlign = TextAlign.Center))
    }
}

@Preview
@Composable
private fun HomePreview() {
    HomeView()
}
