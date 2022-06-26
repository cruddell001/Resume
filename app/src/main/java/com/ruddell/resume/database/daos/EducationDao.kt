package com.ruddell.resume.database.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ruddell.resume.models.Education

@Dao
interface EducationDao {

    @Query("SELECT * FROM education")
    fun selectAll() : LiveData<List<Education>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg item:Education)

    @Query("DELETE FROM education")
    fun clearAll()
}