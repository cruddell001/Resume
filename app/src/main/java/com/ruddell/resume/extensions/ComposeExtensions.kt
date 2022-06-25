package com.ruddell.resume.extensions

import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import com.ruddell.resume.R
import com.ruddell.resume.models.ApiModel
import com.ruddell.resume.models.WorkExperience

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

