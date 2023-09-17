package com.example.courseregistration.repository.courses

import com.example.courseregistration.data.courses.Course
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getAll(): Flow<List<Course>>
}

