package com.example.courseregistration.repository.applications

import com.example.courseregistration.data.applications.Application
import kotlinx.coroutines.flow.Flow

interface ApplicationsRepository {
    fun getAll(): Flow<List<Application>>
    suspend fun createApplication(courseId:String)
    suspend fun deleteApplication(applicationId: String)


}