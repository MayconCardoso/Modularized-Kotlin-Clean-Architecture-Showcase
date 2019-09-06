package com.mctech.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.UserProfileChangeRequest
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User
import com.mctech.domain.services.AuthService
import kotlinx.coroutines.tasks.await

/**
 * @author MAYCON CARDOSO on 2019-08-31.
 */
class AuthRepository(val firebaseAuth: FirebaseAuth) : AuthService {
    override suspend fun fetchLoggedUser() = firebaseAuth.currentUser?.let {
        User(
            it.uid,
            it.displayName.orEmpty(),
            it.phoneNumber,
            it.email,
            it.photoUrl?.toString()
        )
    }

    override suspend fun registerUser(registerUser: RegisterUser): Boolean {
        // Create user
        firebaseAuth.createUserWithEmailAndPassword(
            registerUser.user.email!!, registerUser.password
        ).await()

        // Get user
        val currentUser = firebaseAuth.currentUser

        // Update informations
        currentUser?.apply {
            val profileChangeRequest = UserProfileChangeRequest
                .Builder()
                .setDisplayName(registerUser.user.name)
                .build()

            updateProfile(profileChangeRequest).await()
        }

        return currentUser != null
    }

    override suspend fun login(user: AuthRequest): Boolean {
        val authResult = firebaseAuth.signInWithEmailAndPassword(user.login, user.password).await()
        return authResult?.user != null
    }

    override suspend fun logout() = firebaseAuth.signOut()
}