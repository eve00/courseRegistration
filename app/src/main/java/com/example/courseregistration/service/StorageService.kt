package com.example.courseregistration.service

import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courseRegistrations.CourseRegistration
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val applications : Flow<List<Application>>
    suspend fun createApplication(courseId:String)
    suspend fun deleteApplication(applicationId: String)
}