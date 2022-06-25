package com.ruddell.resume.ui.details

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.models.Skills
import com.ruddell.resume.ui.shared.ThemedCard
import com.ruddell.resume.ui.shared.TitleView

@Composable
fun SkillsContent() {
    val context = LocalContext.current
    val items = ContentRepository.getSkillItems(context).observeAsState()
    val clickedItem = remember { mutableStateOf<Skills?>(null) }

    LazyColumn {
        items.value?.forEach {
            item { SkillItemView(item = it, clickedItem.value == it) {
                clickedItem.value = it
            } }
        }
    }
}

@Composable
private fun SkillItemView(item: Skills, expanded: Boolean, onClick: () -> Unit) {
    ThemedCard(onClick = {
        onClick()
    }) {
        Column(Modifier.padding(8.dp).fillMaxWidth()) {
            TitleView(text = item.categoryName, style = TextStyle(fontSize = 22.sp))
            AnimatedVisibility(visible = expanded) {
                Column {
                    item.itemNames.forEach {
                        TitleView(text = "• $it")
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun SkillItemPreview() {
    val item = Skills(_id = 0, categoryName = "Languages & Frameworks", itemNames = listOf(
        "English",
        "Daddy Speak",
        "Parental Smackdown"
    ))
    SkillItemView(item, true) {}
}
