package com.example.courseregistration.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseregistration.auth.UserAuthorization
import com.example.courseregistration.data.courseRegistrations.CourseRegistration
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.repository.courseRegistrations.CourseRegistrationsRepository
import com.example.courseregistration.repository.courses.CoursesRepository
import com.example.courseregistration.repository.students.StudentsRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.invoke
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val coursesRepository: CoursesRepository,
    private val courseRegistrationsRepository: CourseRegistrationsRepository,
     userAuth:UserAuthorization
) : ViewModel() {
    val studentId = userAuth.getStudentId()
    private val _courses = MutableStateFlow(emptyList<Course>())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()
    private val _courseRegistrations = MutableStateFlow(emptyList<CourseRegistration>())
    val courseRegistrations: StateFlow<List<CourseRegistration>> = _courseRegistrations.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            coursesRepository.getAll().collect{ courses ->
                _courses.value = courses
            }

        }
        viewModelScope.launch(Dispatchers.IO) {
            courseRegistrationsRepository.getAll().collect{ courseRegistrations ->
                _courseRegistrations.value = courseRegistrations
            }
        }
    }}