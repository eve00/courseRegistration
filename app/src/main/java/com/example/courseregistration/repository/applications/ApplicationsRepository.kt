package com.example.courseregistration.repository.applications

import com.example.courseregistration.data.Id
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courses.Course
import kotlinx.coroutines.flow.Flow

interface ApplicationsRepository {
    val applications: Flow<List<Application>>

    suspend fun createApplication(courseId: Id<Course>)

    suspend fun deleteApplication(applicationId: Id<Application>)
}
