package com.example.courseregistration.repository.students

import com.example.courseregistration.data.students.Student
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

class FakeStudentsRepository @Inject constructor(): StudentsRepository {
    override suspend fun get(id: String): Flow<Student> {
        return MutableStateFlow(fakeStudents.find { it.id === id } ?: Student(
            id = "X",
            name = "学生X"
        ))
    }
}
/*
* 区分＋年度３桁＋名列４桁
* */
private val fakeStudents = listOf(Student(id="A0010001",name="学生１"),Student(id="A0010002",name="学生２"),Student(id="A0010003",name="学生３"))