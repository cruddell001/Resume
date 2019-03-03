package com.ruddell.resume.database.daos

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*
import com.ruddell.resume.models.Education
import com.ruddell.resume.models.WorkExperience

@Dao
interface EducationDao {

    @Query("SELECT * FROM education")
    fun selectAll() : LiveData<List<Education>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item:Education)

    @Query("DELETE FROM education")
    fun clearAll()
}