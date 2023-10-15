package com.example.courseregistration.service

import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.applications.ApplicationId
import com.example.courseregistration.data.courseRegistrations.CourseRegistration
import com.example.courseregistration.data.courses.CourseId
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val applications : Flow<List<Application>>
    suspend fun create(courseId:CourseId)
    suspend fun delete(applicationId: ApplicationId)
}