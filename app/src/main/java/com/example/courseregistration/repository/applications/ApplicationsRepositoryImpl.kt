package com.example.courseregistration.repository.applications

import android.util.Log
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.applications.ApplicationId
import com.example.courseregistration.data.courses.CourseId
import com.example.courseregistration.service.StorageService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationsRepositoryImpl @Inject constructor(
    private val db: StorageService
):ApplicationsRepository {
    override val applications: Flow<List<Application>> =
         db.applications


    override suspend fun createApplication(courseId: CourseId) {
        db.create(courseId)

    }

    override suspend fun deleteApplication(applicationId: ApplicationId) {
        db.delete(applicationId)
    }
}