package com.ruddell.resume.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ruddell.resume.models.Skills
import com.ruddell.resume.models.WorkExperience

@Dao
interface SkillsDao {

    @Query("SELECT * FROM skills")
    fun selectAll() : LiveData<List<Skills>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item:Skills)

    @Query("DELETE FROM skills")
    fun clearAll()
}