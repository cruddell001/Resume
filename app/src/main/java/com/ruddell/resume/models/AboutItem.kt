package com.ruddell.resume.models

data class AboutItem(val title: String, val type:ItemType) {
    override fun toString(): String = title
}

enum class ItemType {
    UNSET,
    LICENSES,
    APP_DEVELOPMENT,
    CONTACT_INFO
}
