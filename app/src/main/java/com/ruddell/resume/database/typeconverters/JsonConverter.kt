package com.ruddell.resume.database.typeconverters

import androidx.room.TypeConverter
import com.ruddell.resume.extensions.fromJson
import com.ruddell.resume.extensions.toJson

class JsonConverter {

    @TypeConverter
    fun fromString(value: String): List<String> = value.fromJson() ?: emptyList()

    @TypeConverter
    fun fromList(list: List<String>): String = list.toJson()
}