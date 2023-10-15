package com.example.courseregistration.service.impl

import android.util.Log
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.applications.ApplicationId
import com.example.courseregistration.data.courses.CourseId
import com.example.courseregistration.data.users.Uid
import com.example.courseregistration.service.AccountService
import com.example.courseregistration.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.tasks.await
import javax.inject.Inject


class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService,
) :
    StorageService {
    override val applications: Flow<List<Application>>
        get() = auth.currentUser.flatMapLatest { user ->

            firestore.collection(APPLICATION_COLELCTION).whereEqualTo(STUDENT_ID_FIELD, "anonymous")
                .dataObjects<ApplicationData>()
                .map { data -> mapToApplication(data) }
        }


    override suspend fun create(courseId: CourseId) {
        val id = firestore.collection(APPLICATION_COLELCTION).document().id
        val application = ApplicationData(id, auth.currentUserId.toString(), courseId.toString())
        firestore.collection(APPLICATION_COLELCTION).document().set(application)
    }

    override suspend fun delete(applicationId: ApplicationId) {
        firestore.collection(APPLICATION_COLELCTION).document().delete().await()
    }

    companion object {
        private const val STUDENT_ID_FIELD = "studentId"
        private const val APPLICATION_COLELCTION = "applications"
    }
}

data class ApplicationData(
    val applicationId: String = "",
    val studentId: String = "",
    val courseId: String = "",
)

fun mapToApplication(data: List<ApplicationData>): List<Application> =
    data.map { data ->
        Application(
            ApplicationId(data.applicationId),
            Uid(data.studentId),
            CourseId(data.courseId)
        )
    }
