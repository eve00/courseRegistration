package com.example.courseregistration.repository.courses

import com.example.courseregistration.data.courses.Course
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FakeCoursesRepository @Inject constructor(): CoursesRepository {
    override suspend fun getAll(): Flow<List<Course>> {
        return MutableStateFlow(listOf(Course("講義１"),Course("講義２"),Course("講義３")))
    }

}

