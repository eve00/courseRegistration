package com.example.courseregistration.data.applications

import com.example.courseregistration.data.Id
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.data.users.User

data class Application(
    val applicationId: Id<Application>,
    val studentId: Id<User>,
    val courseId: Id<Course>,
)
