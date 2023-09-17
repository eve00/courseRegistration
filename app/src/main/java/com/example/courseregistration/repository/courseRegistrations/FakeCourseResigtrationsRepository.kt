package com.example.courseregistration.repository.courseRegistrations

import com.example.courseregistration.data.courseRegistrations.CourseRegistration
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.LocalDateTime
import javax.inject.Inject

class FakeCourseResigtrationsRepository @Inject constructor() : CourseRegistrationsRepository {
    override fun getAll(): Flow<List<CourseRegistration>> {
        return MutableStateFlow(fakeCourseRegistrations)
    }

    override suspend fun create(studentId: String, courseId: String) {
        fakeCourseRegistrations.add(
            CourseRegistration(studentId, courseId, System.currentTimeMillis())
        )
    }

    override suspend fun delete(studentId: String, courseId: String) {
        TODO("Not yet implemented")
    }
}

private val fakeCourseRegistrations =
    mutableListOf<CourseRegistration>(CourseRegistration("a", "a", 0))

