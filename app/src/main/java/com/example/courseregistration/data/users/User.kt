package com.example.courseregistration.data.users

data class User(
    val uid: Uid,
    val isAnonymous: Boolean = true
)

data class Uid( val value: String = "")

data class Student (
    val id: String,
    val name: String
)