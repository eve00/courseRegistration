package com.example.courseregistration.repository.tasks

import com.example.courseregistration.data.tasks.Task
import kotlinx.coroutines.flow.Flow

interface TaskRepository {
    fun getAll(): Flow<List<Task>>
    suspend fun create(title: String, content: String)
    suspend fun update(task: Task, title: String, content: String) : Task
    suspend fun delete(task: Task)

}