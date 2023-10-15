package com.example.courseregistration.service.module

import com.example.courseregistration.service.AccountService
import com.example.courseregistration.service.StorageService
import com.example.courseregistration.service.impl.AccountServiceImpl
import com.example.courseregistration.service.impl.StorageServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent


@Module
@InstallIn(SingletonComponent::class)
abstract class ServiceModule {
    @Binds
    abstract fun provideAccountService(impl: AccountServiceImpl): AccountService


    @Binds
    abstract fun provideStorageService(impl: StorageServiceImpl): StorageService

}