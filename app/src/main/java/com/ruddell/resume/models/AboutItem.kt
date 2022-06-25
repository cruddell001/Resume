package com.ruddell.resume.models

import android.content.Context
import com.ruddell.resume.R

data class AboutItem(val title: String, val type:ItemType) {
    override fun toString(): String = title
}

object AboutContent {
    fun items(context: Context): List<AboutItem> = listOf(
        AboutItem(context.getString(R.string.third_party_licenses), ItemType.LICENSES),
        AboutItem(context.getString(R.string.app_development), ItemType.APP_DEVELOPMENT),
        AboutItem(context.getString(R.string.contact_info), ItemType.CONTACT_INFO)
    )
}

enum class ItemType {
    UNSET,
    LICENSES,
    APP_DEVELOPMENT,
    CONTACT_INFO
}
