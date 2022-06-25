package com.ruddell.resume.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.annotation.NonNull

@Entity
class Skills (
    @PrimaryKey val _id:Int?,
    @NonNull val categoryName:String,
    @NonNull val itemNames:List<String>
): ApiModel