package com.example.courseregistration.service.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.courseregistration.data.Id
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.service.AccountService
import com.example.courseregistration.service.StorageService
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
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


    override suspend fun create(courseId: Id<Course>) {
        val id = firestore.collection(APPLICATION_COLELCTION).document().id
        val application = ApplicationData(
            id,
            auth.currentUserId.toString(),
            courseId.toString(),
            System.currentTimeMillis()
        )
        firestore.collection(APPLICATION_COLELCTION).document().set(application)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }

    override suspend fun delete(applicationId: Id<Application>) {
        firestore.collection(APPLICATION_COLELCTION).document(applicationId.toString()).delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
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
    val createdAt: Long = 0L,
)

fun mapToApplication(data: List<ApplicationData>): List<Application> =
    data.map { data ->
        Application(
            Id(data.applicationId),
            Id(data.studentId),
            Id(data.courseId)
        )
    }
