package com.example.courseregistration.screen.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.courseregistration.data.Task
import com.example.courseregistration.repository.TaskRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class homeViewModel @Inject constructor(
    private val repository: TaskRepository
) : ViewModel() {
    private val _tasks = MutableStateFlow(emptyList<Task>())
    val tasks: StateFlow<List<Task>> = _tasks.asStateFlow()
    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getAll().collect{tasks ->
                _tasks.value = tasks
            }
        }
    }

    fun createTask(title:String, content:String){
        viewModelScope.launch(Dispatchers.IO) {
        repository.create(title,content)
        }
    }

    fun updateTask(task: Task, title:String, content:String = task.content ?: ""){
        viewModelScope.launch(Dispatchers.IO) {
            repository.update(task, title, content)
        }
    }

    fun deleteTask(task: Task){
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(task)
        }
    }
}