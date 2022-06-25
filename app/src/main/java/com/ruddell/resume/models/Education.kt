package com.ruddell.resume.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Education (
    @PrimaryKey val _id:Int?,
    val schoolName:String?,
    val degreeName:String?,
    val graduationDate:String?,
    val gpa:Float?,
    val website:String?
): ApiModel