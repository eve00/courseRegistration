package com.example.courseregistration.repository.students

import com.example.courseregistration.data.students.Student
import kotlinx.coroutines.flow.Flow

interface StudentsRepository {
    suspend fun get(id: String): Flow<Student>
}