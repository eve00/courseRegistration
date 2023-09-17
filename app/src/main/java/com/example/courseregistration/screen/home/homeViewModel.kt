package com.example.courseregistration.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.repository.courses.CoursesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: CoursesRepository
) : ViewModel() {
    private val _courses = MutableStateFlow(emptyList<Course>())
    val courses: StateFlow<List<Course>> = _courses.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().collect{courses ->
                _courses.value = courses
            }
        }
    }}