package com.ruddell.resume.ui.details

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruddell.resume.R
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.extensions.cardBackgroundColor
import com.ruddell.resume.extensions.titleColor
import com.ruddell.resume.models.Education
import com.ruddell.resume.ui.MainActivity

@Composable
fun EducationContent() {
    val context = LocalContext.current
    val items = ContentRepository.getEducationItems(context).observeAsState()
    LazyColumn {
        items.value?.forEach {
            item { EducationView(item = it) }
        }
    }
}

@Composable
fun EducationView(item: Education) {
    val context = LocalContext.current
    Card(Modifier.padding(8.dp).background(cardBackgroundColor).clickable {
        launchWebsite(context, item.website)
    }) {
        Column(Modifier.padding(8.dp).fillMaxWidth()) {
            Row {
                Column(Modifier.fillMaxWidth().weight(1f)) {
                    Text(
                        text = item.degreeName ?: "",
                        style = TextStyle(titleColor, fontWeight = FontWeight.Medium, fontSize = 18.sp),
                    )
                    Text(
                        text = item.schoolName ?: "",
                        style = TextStyle(titleColor, fontStyle = FontStyle.Italic),
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
                Column {
                    Text(
                        text = item.graduationDate ?: "",
                        style = TextStyle(titleColor)
                    )
                    item.gpa?.let {
                        Text(
                            text = "GPA: $it",
                            style = TextStyle(titleColor)
                        )
                    }
                }
            }
        }
    }
}

private fun launchWebsite(context: Context, url:String?) {
    url ?: return
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    (context as? MainActivity)?.startActivity(browserIntent)
}

@Preview
@Composable
private fun EducationPreview() {
    Column(Modifier.fillMaxSize().background(colorResource(id = R.color.educationBackground))) {
        EducationView(item = Education(_id = null, schoolName = "Parent Academy", degreeName = "Father of the Year", graduationDate = "June 2012", gpa = null, website = null))
        EducationView(item = Education(_id = null, schoolName = "The Ohio State University", degreeName = "Master of Fatherhood and Parenting Nanodegree", graduationDate = "June 29, 2012", gpa = 3.99f, website = null))
    }
}
