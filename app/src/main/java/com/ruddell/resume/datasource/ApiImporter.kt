package com.ruddell.resume.datasource

import android.content.Context
import android.util.Log
import com.ruddell.resume.BuildConfig
import com.ruddell.resume.database.repositories.ContentRepository
import com.ruddell.resume.models.Resume
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.http.GET
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine


object ApiImporter : CoroutineScope {
    const val TAG = "ApiImporter"
    override val coroutineContext: CoroutineContext get() = Dispatchers.IO

    private fun getService(): ResumeService? = RetrofitClient.getClient(BuildConfig.CONTENT_SERVER_URL)?.create(ResumeService::class.java)

    private suspend fun getContentFromServer() : Resume? = suspendCoroutine {continuation ->
        getService()?.resume?.enqueue(object : Callback<Resume> {
            override fun onFailure(call: Call<Resume>, t: Throwable) {
                continuation.resume(null)
            }

            override fun onResponse(call: Call<Resume>, response: Response<Resume>) {
                continuation.resume(response.body())
            }

        })
    }

    fun import(context:Context) {
        launch {
            val resume = getContentFromServer() ?: return@launch
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


object RetrofitClient {

    private var retrofit: Retrofit? = null

    fun getClient(baseUrl: String): Retrofit? {
        if (retrofit == null) {
            retrofit = Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        return retrofit
    }
}

interface ResumeService {

    @get:GET("/resume/json")
    val resume: Call<Resume>
}