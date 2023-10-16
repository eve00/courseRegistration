package com.example.courseregistration.service

import com.example.courseregistration.data.Id
import com.example.courseregistration.data.users.User
import kotlinx.coroutines.flow.Flow

interface AccountService {
    val currentUserId: Id<User>
    val hasUser: Boolean

    val currentUser: Flow<User>

    suspend fun createAnonymousAccount()
}
