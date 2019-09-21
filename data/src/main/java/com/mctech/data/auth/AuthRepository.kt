package com.mctech.data.auth

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthInvalidUserException
import com.google.firebase.auth.UserProfileChangeRequest
import com.mctech.domain.errors.AuthException
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User
import com.mctech.domain.services.AuthService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext

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

    override suspend fun registerUser(registerUser: RegisterUser) = withContext(Dispatchers.IO){
        try{
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

            currentUser != null
        }
        catch (e : Exception){
            when(e){
                is RuntimeException -> throw e
                else -> throw AuthException.UnknownAuthException
            }

        }
    }

    override suspend fun login(user: AuthRequest) = withContext(Dispatchers.IO){
        try{
            val authResult = firebaseAuth.signInWithEmailAndPassword(user.email, user.password).await()
            authResult?.user != null
        }
        catch (e : Exception){
            when(e){
                is IllegalArgumentException -> throw AuthException.EmptyFormValueException
                is FirebaseAuthInvalidCredentialsException -> throw AuthException.InvalidEmailFormatException
                is FirebaseAuthInvalidUserException -> throw AuthException.UserNotFoundException
                else -> throw AuthException.UnknownAuthException
            }

        }
    }

    override suspend fun logout() = firebaseAuth.signOut()
}