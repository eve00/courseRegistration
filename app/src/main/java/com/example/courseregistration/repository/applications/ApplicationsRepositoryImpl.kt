package com.example.courseregistration.repository.applications

import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.service.StorageService
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ApplicationsRepositoryImpl @Inject constructor(
    private val db: StorageService
):ApplicationsRepository {
    override fun getAll(): Flow<List<Application>> {
        return db.applications
    }

    override suspend fun createApplication(courseId: String) {
        db.create(courseId)
    }

    override suspend fun deleteApplication(applicationId: String) {
        db.delete(applicationId)
    }
}