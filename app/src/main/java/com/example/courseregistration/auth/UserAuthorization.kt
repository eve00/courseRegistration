package com.example.courseregistration.auth

import com.example.courseregistration.repository.students.StudentsRepository
import javax.inject.Inject

class UserAuthorization @Inject constructor(
    private val studentsRepository: StudentsRepository
) {
    val fakeStudentId = "A0010001"

    public inline fun getStudentId(): String{return fakeStudentId}
}