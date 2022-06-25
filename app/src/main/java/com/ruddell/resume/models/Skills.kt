package com.ruddell.resume.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull
import kotlinx.serialization.Serializable

@Serializable
@Entity
class Skills (
    @PrimaryKey val _id:Int? = null,
    @NonNull val categoryName:String = "",
    @NonNull val itemNames:List<String> = emptyList()
): ApiModel