package com.example.courseregistration.repository

import com.example.courseregistration.data.Task
import com.example.courseregistration.data.TaskDao
import dagger.Binds
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject
import javax.inject.Singleton

class TaskRepositoryImpl @Inject constructor(
    private val dao: TaskDao
) : TaskRepository {
    override fun getAll(): Flow<List<Task>> {
        return dao.getAll()
    }

    override suspend fun create(title: String, content: String) {
        val now = System.currentTimeMillis()
        val task = Task(title= title, content=content, created = now, modified = now)
        withContext(Dispatchers.IO) {
            dao.insert(task)
        }    }

    override suspend fun update(task: Task, title: String, content: String): Task {
        val now = System.currentTimeMillis()
        val updatedTask = Task(
            id = task.id,
            title = title,
            content = content,
            created = task.created,
            modified = now
        )
        withContext(Dispatchers.IO) {
            dao.update(updatedTask)
        }
        return updatedTask
    }

    override suspend fun delete(task: Task) {
        dao.delete(task)
    }

}