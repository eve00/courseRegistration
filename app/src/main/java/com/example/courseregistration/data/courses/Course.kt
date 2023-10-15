package com.example.courseregistration.data.courses

data class Course(
    val courseId: CourseId,
    val title: String
)

data class CourseId (val value: String)