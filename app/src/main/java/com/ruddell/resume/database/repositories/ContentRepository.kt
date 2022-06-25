package com.ruddell.resume.database.repositories

import androidx.lifecycle.LiveData
import android.content.Context
import androidx.annotation.WorkerThread
import androidx.lifecycle.MutableLiveData
import com.ruddell.resume.database.AppDatabase
import com.ruddell.resume.models.AboutContent
import com.ruddell.resume.models.Education
import com.ruddell.resume.models.Skills
import com.ruddell.resume.models.WorkExperience
import com.ruddell.resume.ui.Details

object ContentRepository {

    fun getDetailItems(details: Details, context: Context) = when (details) {
        Details.WORK_EXPERIENCE -> getWorkItems(context)
        Details.EDUCATION -> getEducationItems(context)
        Details.SKILLS -> getSkillItems(context)
        Details.ABOUT -> MutableLiveData(AboutContent.items(context))
    }
    fun getWorkItems(context: Context) : LiveData<List<WorkExperience>> = AppDatabase[context].workExperienceDao().selectAll()
    fun getEducationItems(context: Context) : LiveData<List<Education>> = AppDatabase[context].educationDao().selectAll()
    fun getSkillItems(context: Context) : LiveData<List<Skills>> = AppDatabase[context].skillsDao().selectAll()

    @WorkerThread
    fun deleteAll(context: Context) {
        AppDatabase[context].workExperienceDao().clearAll()
        AppDatabase[context].educationDao().clearAll()
        AppDatabase[context].skillsDao().clearAll()
    }

    @WorkerThread
    fun addWorkItem(context: Context, item:WorkExperience) = AppDatabase[context].workExperienceDao().insert(item)

    @WorkerThread
    fun addEducationItem(context: Context, item:Education) = AppDatabase[context].educationDao().insert(item)

    @WorkerThread
    fun addSkillItem(context: Context, item:Skills) = AppDatabase[context].skillsDao().insert(item)
}