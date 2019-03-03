package com.ruddell.resume.models

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import android.support.annotation.NonNull

@Entity
class Skills (
    @PrimaryKey val _id:Int?,
    @NonNull val categoryName:String,
    @NonNull val itemNames:List<String>
)