package com.ruddell.resume.datasource

import android.content.Context
import android.util.Log
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.models.Resume
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json
import kotlin.coroutines.CoroutineContext


object ApiImporter : CoroutineScope {
    private const val TAG = "ApiImporter"
    override val coroutineContext: CoroutineContext get() = Dispatchers.IO

    private val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })
        }
        install(Logging)
    }

    private suspend fun downloadContent(): Resume? =
        try { client.get("https://my-resume.app/data").body() }
        catch (e: Exception) {
            e.printStackTrace()
            FirebaseCrashlytics.getInstance().recordException(e)
            null
        }

    fun import(context:Context) {
        launch {
            val resume = downloadContent() ?: return@launch
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
}
