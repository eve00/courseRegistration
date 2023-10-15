package com.example.courseregistration.data.users

import com.example.courseregistration.data.Id

data class User(
    val uid: Id<User>,
    val isAnonymous: Boolean = true
)