package com.ruddell.resume.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruddell.resume.models.Skills

@Dao
interface SkillsDao {

    @Query("SELECT * FROM skills")
    fun selectAll() : LiveData<List<Skills>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item:Skills)

    @Query("DELETE FROM skills")
    fun clearAll()
}