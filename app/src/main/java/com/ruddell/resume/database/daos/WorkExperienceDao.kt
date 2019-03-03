package com.ruddell.resume.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ruddell.resume.models.WorkExperience

@Dao
interface WorkExperienceDao {

    @Query("SELECT * FROM workexperience")
    fun selectAll() : LiveData<List<WorkExperience>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item:WorkExperience)

    @Query("DELETE FROM workexperience")
    fun clearAll()
}