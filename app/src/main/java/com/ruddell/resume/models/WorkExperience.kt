package com.ruddell.resume.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull
import kotlinx.serialization.Serializable


@Serializable
@Entity
data class WorkExperience (
    @PrimaryKey val _id:Int? = null,
    @NonNull val position:String = "",
    val companyName:String? = null,
    val dates:String? = null,
    val description:String? = null
): ApiModel