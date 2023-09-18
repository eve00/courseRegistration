package com.example.courseregistration.repository.courseRegistrations

import com.example.courseregistration.data.courseRegistrations.CourseRegistration
import com.example.courseregistration.data.tasks.Task
import kotlinx.coroutines.flow.Flow

interface CourseRegistrationsRepository {
    fun getAll(): Flow<List<CourseRegistration>>
    suspend fun create(studentId:String,courseId:String)
    suspend fun delete(studentId:String,courseId:String)


}