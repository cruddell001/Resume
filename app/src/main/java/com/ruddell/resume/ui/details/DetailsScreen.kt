package com.ruddell.resume.ui.details

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ruddell.resume.R
import com.ruddell.resume.extensions.*
import com.ruddell.resume.ui.Details

@Composable
fun DetailsView(details: Details) {
    Column(Modifier.fillMaxSize().background(details.backgroundColor())) {
        Row(verticalAlignment = Alignment.CenterVertically, modifier = Modifier.padding(top = 24.dp, start = 24.dp)) {
            Image(
                painter = details.iconImage(),
                contentDescription = stringResource(R.string.icon),
                modifier = Modifier.size(72.dp)
            )
            Text(
                text = details.title(),
                modifier = Modifier.padding(start = 8.dp),
                style = TextStyle(fontSize = 24.sp)
            )
        }
        when (details) {
            Details.WORK_EXPERIENCE -> WorkExperienceContent()
            Details.EDUCATION -> EducationContent()
            Details.SKILLS -> SkillsContent()
            Details.ABOUT -> AboutContent()
        }
    }
}



@Preview
@Composable
private fun DetailsPreview() {
    DetailsView(details = Details.WORK_EXPERIENCE)
}
