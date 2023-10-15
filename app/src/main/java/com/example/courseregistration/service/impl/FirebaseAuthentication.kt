package com.example.courseregistration.service.impl

import com.example.courseregistration.data.Id
import com.example.courseregistration.data.users.User
import com.example.courseregistration.service.AccountService
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseAuthentication @Inject constructor(private val auth: FirebaseAuth) : AccountService {

    override val currentUserId: Id<User>
        get() = Id(auth.currentUser?.uid ?: "anonymous")

    override val hasUser: Boolean
        get() = auth.currentUser != null


    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(Id(it.uid), it.isAnonymous) } ?: User(Id("")))
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }


    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

}
