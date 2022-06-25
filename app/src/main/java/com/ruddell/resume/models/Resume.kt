package com.ruddell.resume.models

import kotlinx.serialization.Serializable

@Serializable
data class Resume (
    val workExperience:List<WorkExperience>? = null,
    val skills:List<Skills>? = null,
    val education:List<Education>? = null
): ApiModel