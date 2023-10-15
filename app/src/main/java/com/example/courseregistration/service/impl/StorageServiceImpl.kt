package com.example.courseregistration.service.impl

import androidx.compose.ui.input.key.Key.Companion.I
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courseRegistrations.CourseRegistration
import com.example.courseregistration.service.AccountService
import com.example.courseregistration.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService,
) :
    StorageService {
    override val applications: Flow<List<Application>>
        get() =
            auth.currentUser.flatMapLatest { user ->
                firestore.collection(APPLICATION_COLELCTION).whereEqualTo(USER_ID_FIELD, user.id).dataObjects()
            }
    override suspend fun create(courseId: String) {
        val id = firestore.collection(APPLICATION_COLELCTION).document().id
        val application = Application(id, auth.currentUserId, courseId)
        firestore.collection(APPLICATION_COLELCTION).document().set(application)
    }

    override suspend fun delete(applicationId: String) {
        firestore.collection(APPLICATION_COLELCTION).document().delete().await()
    }

    companion object{
        private const val USER_ID_FIELD = "userId"
        private const val APPLICATION_COLELCTION = "applications"
    }

}