package com.example.courseregistration.service

import com.example.courseregistration.data.users.Uid
import com.example.courseregistration.data.users.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: Uid
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun createAnonymousAccount()

}