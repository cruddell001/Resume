package com.ruddell.resume.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull
import android.support.annotation.Nullable

@Entity
data class WorkExperience (
    @PrimaryKey val _id:Int?,
    @NonNull val position:String,
    val companyName:String?,
    val dates:String?,
    val description:String?
)