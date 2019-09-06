package com.mctech.domain.services

import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
interface AuthService {
    suspend fun fetchLoggedUser(): User?
    suspend fun registerUser(registerUser: RegisterUser) : Boolean
    suspend fun login(user: AuthRequest) : Boolean
    suspend fun logout()
}