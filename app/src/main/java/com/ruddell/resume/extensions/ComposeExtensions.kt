package com.ruddell.resume.extensions

import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.ruddell.resume.R
import com.ruddell.resume.models.ApiModel
import com.ruddell.resume.models.WorkExperience
import com.ruddell.resume.ui.Details

// colors
val cardBackgroundColor: Color @Composable get() = colorResource(id = R.color.cardBackground)
val titleColor: Color @Composable get() = colorResource(id = R.color.titleColor)

@Composable
fun ApiModel.DetailItemViewCollapsed() {
    Card() {
        when (this) {
            is WorkExperience -> {

            }
        }
    }

}

@Composable
fun Details.backgroundColor() = colorResource(when (this) {
    Details.WORK_EXPERIENCE -> R.color.workBackground
    Details.EDUCATION -> R.color.educationBackground
    Details.SKILLS -> R.color.skillsBackground
    Details.ABOUT -> R.color.aboutBackground
})

@Composable
fun Details.iconImage() = when (this) {
    Details.WORK_EXPERIENCE -> painterResource(R.drawable.work_icon)
    Details.EDUCATION -> painterResource(R.drawable.education_icon)
    Details.ABOUT -> painterResource(R.drawable.info_icon)
    Details.SKILLS -> painterResource(R.drawable.skills_icon)
}

@Composable
fun Details.title() = stringResource(
    id = when (this) {
        Details.WORK_EXPERIENCE -> R.string.work_experience
        Details.EDUCATION -> R.string.education
        Details.ABOUT -> R.string.about
        Details.SKILLS -> R.string.skills
    }
)
