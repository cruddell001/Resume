package com.ruddell.resume.database

import androidx.room.*
import android.content.Context
import com.ruddell.resume.database.daos.EducationDao
import com.ruddell.resume.database.daos.SkillsDao
import com.ruddell.resume.database.daos.WorkExperienceDao
import com.ruddell.resume.database.typeconverters.JsonConverter
import com.ruddell.resume.models.Education
import com.ruddell.resume.models.Skills
import com.ruddell.resume.models.WorkExperience

@Database(
    entities = arrayOf(WorkExperience::class, Education::class, Skills::class),
    version = AppDatabase.DB_VERSION
)
@TypeConverters(JsonConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workExperienceDao() : WorkExperienceDao
    abstract fun skillsDao() : SkillsDao
    abstract fun educationDao() : EducationDao

    companion object {
        const val DB_VERSION = 1
        const val DB_NAME = "resume.db"
        @Volatile private var INSTANCE: AppDatabase? = null

        @Synchronized
        operator fun get(context: Context): AppDatabase {
            if (INSTANCE == null) INSTANCE = create(context, false)
            return INSTANCE!!
        }

        internal fun create(context: Context, memoryOnly: Boolean): AppDatabase {
            val b: RoomDatabase.Builder<AppDatabase>

            if (memoryOnly)
                b = Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
            else
                b = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, DB_NAME)

            return b.build()
        }
    }
}