package com.example.courseregistration.data.courses

import com.example.courseregistration.data.Id

data class Course(
    val courseId: Id<Course>,
    val title: String
)

