package com.example.courseregistration.service.impl

import com.example.courseregistration.data.users.Uid
import com.example.courseregistration.service.AccountService
import com.example.courseregistration.data.users.User
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class AccountServiceImpl @Inject constructor(private val auth: FirebaseAuth) : AccountService {

    override val currentUserId: Uid
        get() = Uid(auth.currentUser?.uid ?: "anonymous")

    override val hasUser: Boolean
        get() = auth.currentUser != null


    override val currentUser: Flow<User>
        get() = callbackFlow {
            val listener =
                FirebaseAuth.AuthStateListener { auth ->
                    this.trySend(auth.currentUser?.let { User(Uid(it.uid), it.isAnonymous) } ?: User(Uid()))
                }
            auth.addAuthStateListener(listener)
            awaitClose { auth.removeAuthStateListener(listener) }
        }


    override suspend fun createAnonymousAccount() {
        auth.signInAnonymously().await()
    }

}
