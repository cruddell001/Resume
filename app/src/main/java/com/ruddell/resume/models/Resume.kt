package com.ruddell.resume.models

data class Resume (
    val workExperience:List<WorkExperience>?,
    val skills:List<Skills>?,
    val education:List<Education>?
): ApiModel