package com.example.courseregistration.service.module

import com.example.courseregistration.service.AccountService
import com.example.courseregistration.service.StorageService
import com.example.courseregistration.service.impl.FirebaseAuthentication
import com.example.courseregistration.service.impl.FirestoreApplications
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: FirebaseAuthentication): AccountService


    @Binds
    abstract fun provideStorageService(impl: FirestoreApplications): StorageService

}