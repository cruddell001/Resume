package com.ruddell.resume.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull
import androidx.annotation.Nullable

@Entity
data class WorkExperience (
    @PrimaryKey val _id:Int?,
    @NonNull val position:String,
    val companyName:String?,
    val dates:String?,
    val description:String?
)