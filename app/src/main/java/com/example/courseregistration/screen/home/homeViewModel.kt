package com.example.courseregistration.screen.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseregistration.auth.UserAuthorization
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.data.courses.CourseId
import com.example.courseregistration.repository.applications.ApplicationsRepository
import com.example.courseregistration.repository.courses.CoursesRepository
import com.example.courseregistration.service.StorageService
import dagger.hilt.android.lifecycle.HiltViewModel
import io.grpc.Context.Storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val applicationsRepository: ApplicationsRepository,
) : ViewModel() {

    private val _applications = MutableStateFlow(emptyList<Application>())
    val applications: StateFlow<List<Application>> =
        _applications.asStateFlow()

    val courses: List<Course> = listOf(Course(CourseId("c001"), "Course1"),Course(CourseId("c002"), "Course2"),Course(CourseId("c003"), "Course3"))

    init {
        viewModelScope.launch(Dispatchers.IO) {
            applicationsRepository.applications.collect { applications ->
                _applications.value = applications
            }
        }
    }

    fun createCourseRegistration(courseId: CourseId){
        viewModelScope.launch(Dispatchers.IO) {
            applicationsRepository.createApplication(courseId)
        }
    }
}