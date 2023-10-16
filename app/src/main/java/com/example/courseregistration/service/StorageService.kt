package com.example.courseregistration.service

import com.example.courseregistration.data.Id
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courses.Course
import kotlinx.coroutines.flow.Flow

interface StorageService {
    val applications: Flow<List<Application>>

    suspend fun create(courseId: Id<Course>)

    suspend fun delete(applicationId: Id<Application>)
}
