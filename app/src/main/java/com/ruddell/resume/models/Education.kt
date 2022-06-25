package com.ruddell.resume.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.Serializable

@Serializable
@Entity
data class Education (
    @PrimaryKey val _id:Int? = null,
    val schoolName:String? = null,
    val degreeName:String? = null,
    val graduationDate:String? = null,
    val gpa:Float? = null,
    val website:String? = null
): ApiModel