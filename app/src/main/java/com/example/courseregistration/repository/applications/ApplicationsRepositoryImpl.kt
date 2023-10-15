package com.example.courseregistration.repository.applications

import com.example.courseregistration.data.Id
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.service.StorageService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationsRepositoryImpl @Inject constructor(
    private val db: StorageService
):ApplicationsRepository {
    override val applications: Flow<List<Application>> =
         db.applications


    override suspend fun createApplication(courseId: Id<Course>) {
        db.create(courseId)

    }

    override suspend fun deleteApplication(applicationId: Id<Application>) {
        db.delete(applicationId)
    }
}