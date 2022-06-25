package com.ruddell.resume.datasource

import android.content.Context
import androidx.annotation.WorkerThread
import android.util.Log
import com.google.gson.GsonBuilder
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.models.Resume

object LocalImporter {
    const val TAG = "LocalImporter"

    //loads data from the local json file so we have initial content when the app launches - later we'll see if there's more up to date data on the server
    @WorkerThread
    fun import(context: Context, resourceId:Int) {
        Log.d(TAG, "import()")
        val inSt = context.resources.openRawResource(resourceId)
        val contents = inSt.bufferedReader().use { it.readText() }

        val gson = GsonBuilder().create()
        val resume = gson.fromJson(contents , Resume::class.java)

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