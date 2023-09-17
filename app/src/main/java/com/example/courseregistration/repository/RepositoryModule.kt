package com.example.courseregistration.repository

import com.example.courseregistration.repository.courses.CoursesRepository
import com.example.courseregistration.repository.courses.FakeCoursesRepository
import com.example.courseregistration.repository.students.FakeStudentsRepository
import com.example.courseregistration.repository.students.StudentsRepository
import com.example.courseregistration.repository.tasks.TaskRepository
import com.example.courseregistration.repository.tasks.TaskRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFakeStudentsRepository(fakeImpl: FakeStudentsRepository): StudentsRepository
    @Binds
    @Singleton
    abstract fun bindFakeCourseRepository(fakeImpl: FakeCoursesRepository): CoursesRepository

    @Binds
    @Singleton
    abstract fun bindTaskRepository(impl: TaskRepositoryImpl): TaskRepository


}