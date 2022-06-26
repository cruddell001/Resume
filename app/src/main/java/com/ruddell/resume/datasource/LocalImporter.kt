package com.ruddell.resume.datasource

import android.content.Context
import androidx.annotation.WorkerThread
import android.util.Log
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.extensions.fromJson
import com.ruddell.resume.models.Resume

object LocalImporter {
    private const val TAG = "LocalImporter"

    // loads data from the local json file so we have initial content when the app launches - later we'll see if there's more up to date data on the server
    @WorkerThread
    fun import(context: Context, resourceId:Int) {
        Log.d(TAG, "import()")
        val inSt = context.resources.openRawResource(resourceId)
        val contents = inSt.bufferedReader().use { it.readText() }


        val resume: Resume = contents.fromJson() ?: Resume()

        Log.d(TAG, "found ${resume.workExperience?.size} work items")
        Log.d(TAG, "found ${resume.education?.size} education items")
        Log.d(TAG, "found ${resume.skills?.size} skill items")

        ContentRepository.deleteAll(context)

        resume.workExperience?.forEach {
            ContentRepository.addWorkItem(context, it)
        }

        resume.education?.forEach {
            ContentRepository.addEducationItem(context, it)
        }

        resume.skills?.forEach {
            ContentRepository.addSkillItem(context, it)
        }

    }

}