package com.ruddell.resume.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruddell.resume.R
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.extensions.cardBackgroundColor
import com.ruddell.resume.extensions.titleColor
import com.ruddell.resume.models.WorkExperience

@Composable
fun WorkExperienceContent() {
    val context = LocalContext.current
    val items = ContentRepository.getWorkItems(context).observeAsState()
    val clickedItem = remember { mutableStateOf<WorkExperience?>(null) }
    LazyColumn {
        items.value?.forEach {
            item { WorkItemView(item = it, expanded = clickedItem.value == it) {
                clickedItem.value = it
            } }
        }
    }
}

@Composable
private fun WorkItemView(item: WorkExperience, expanded: Boolean, onClick: () -> Unit) {
    Card(Modifier.padding(8.dp).background(cardBackgroundColor).clickable {
        onClick()
    }) {
        Column(Modifier.padding(8.dp).fillMaxWidth()) {
            Row(Modifier.height(IntrinsicSize.Min)) {
                Column(Modifier.fillMaxHeight(), verticalArrangement = Arrangement.SpaceBetween) {
                    Text(
                        text = item.companyName ?: "",
                        style = TextStyle(titleColor, fontWeight = FontWeight.Medium, fontSize = 18.sp),
                    )
                    Text(
                        text = item.position,
                        style = TextStyle(titleColor, fontStyle = FontStyle.Italic),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Spacer(Modifier.fillMaxWidth().weight(1f))
                Text(item.dates ?: "", Modifier.padding(end = 8.dp), textAlign = TextAlign.Center)
            }
            AnimatedVisibility(visible = expanded) {
                Text(
                    text = item.description ?: "",
                    modifier = Modifier.padding(8.dp),
                    style = TextStyle(titleColor)
                )
            }
        }
    }
}

private val previewExperience = WorkExperience(
    _id = 0,
    position = "Chief Engineer Officer",
    companyName = "My House, LLC",
    dates = "May 2022\n-\nPresent",
    description = "• Chief engineer for my household\n• Setup technology for all household members\n• Troubleshoot as required"
)

@Preview
@Composable
private fun WorkItemCollapsedPreview() {
    Column(Modifier.fillMaxSize().background(colorResource(id = R.color.workBackground))) {
        WorkItemView(item = previewExperience, expanded = true) {}
    }

}
