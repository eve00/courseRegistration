package com.example.courseregistration.service.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.example.courseregistration.data.Id
import com.example.courseregistration.data.applications.Application
import com.example.courseregistration.data.courses.Course
import com.example.courseregistration.service.AccountService
import com.example.courseregistration.service.StorageService
import com.google.firebase.Timestamp
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ServerTimestamp
import com.google.firebase.firestore.ktx.dataObjects
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import java.util.Date
import javax.inject.Inject


class StorageServiceImpl @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: AccountService,
) :
    StorageService {
    @OptIn(ExperimentalCoroutinesApi::class)
    override val applications: Flow<List<Application>>
        get() =

                firestore.collection(APPLICATION_COLLELCTION)
                    .whereEqualTo(STUDENT_ID_FIELD, auth.currentUserId.value)
                    .dataObjects<ApplicationData>()
                    .map { data -> mapToApplication(data) }



    override suspend fun create(courseId: Id<Course>) {
        val id = firestore.collection(APPLICATION_COLLELCTION).document().id
        val application = ApplicationData(
            id,
            auth.currentUserId.value,
            courseId.value
        )
        firestore.collection(APPLICATION_COLLELCTION).document(id).set(application)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot written with ID: $documentReference")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

    }

    override suspend fun delete(applicationId: Id<Application>) {
        firestore.collection(APPLICATION_COLLELCTION).document(applicationId.value).delete()
            .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully deleted!") }
            .addOnFailureListener { e -> Log.w(TAG, "Error deleting document", e) }
    }

    companion object {
        private const val STUDENT_ID_FIELD = "studentId"
        private const val APPLICATION_COLLELCTION = "applications"
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
            Id(data.applicationId),
            Id(data.studentId),
            Id(data.courseId)
        )
    }
