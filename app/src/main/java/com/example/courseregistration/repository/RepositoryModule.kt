package com.example.courseregistration.repository

import com.example.courseregistration.repository.applications.ApplicationsRepository
import com.example.courseregistration.repository.applications.ApplicationsRepositoryImpl
import com.example.courseregistration.repository.students.FakeStudentsRepository
import com.example.courseregistration.repository.students.StudentsRepository
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
    abstract fun bindApplicationReository(impl: ApplicationsRepositoryImpl): ApplicationsRepository


}