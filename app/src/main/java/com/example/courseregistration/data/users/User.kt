package com.example.courseregistration.data.users

data class User(
    val id: String = "",
    val isAnonymous: Boolean = true
)

data class Student (
    val id: String,
    val name: String
)