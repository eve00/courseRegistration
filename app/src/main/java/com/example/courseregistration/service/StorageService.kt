package com.example.courseregistration.service

import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courseRegistrations.CourseRegistration
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val applications : Flow<List<Application>>
    suspend fun create(courseId:String)
    suspend fun delete(applicationId: String)
}