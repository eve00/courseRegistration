package com.example.courseregistration.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseregistration.data.Id
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.repository.applications.ApplicationsRepository
import com.example.courseregistration.service.AccountService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val applicationsRepository: ApplicationsRepository,
    private val auth: AccountService
) : ViewModel() {
    val currentUserId = auth.currentUserId.value

    private val _applications = MutableStateFlow(emptyList<Application>())
    val applications: StateFlow<List<Application>> =
        _applications.asStateFlow()

    val courses: List<Course> = listOf(Course(Id("c001"), "Course1"),Course(Id("c002"), "Course2"),Course(Id("c003"), "Course3"))

    init {
        viewModelScope.launch(Dispatchers.IO) {
            applicationsRepository.applications.collect { applications ->
                _applications.value = applications
            }
        }
    }

    fun createCourseRegistration(courseId: Id<Course>){
        viewModelScope.launch(Dispatchers.IO) {
            applicationsRepository.createApplication(courseId)
        }
    }
}