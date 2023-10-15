package com.example.courseregistration.repository.applications

import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.applications.ApplicationId
import com.example.courseregistration.data.courses.CourseId
import kotlinx.coroutines.flow.Flow

interface ApplicationsRepository {
    val applications : Flow<List<Application>>
    suspend fun createApplication(courseId:CourseId)
    suspend fun deleteApplication(applicationId: ApplicationId)


}