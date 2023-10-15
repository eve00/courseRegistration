package com.example.courseregistration.data.applications

import com.example.courseregistration.data.courses.CourseId
import com.example.courseregistration.data.users.Uid

data class Application (
    val applicationId: ApplicationId,
    val studentId: Uid,
    val courseId: CourseId,
)

data class ApplicationId(val value: String)

