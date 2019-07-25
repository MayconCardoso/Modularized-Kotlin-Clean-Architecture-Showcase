package com.mctech.domain.auth.services

import com.mctech.domain.auth.model.AuthRequest
import com.mctech.domain.auth.model.User

/**
 * @author MAYCON CARDOSO on 2019-07-25.
 */
interface AuthService {
    suspend fun fetchLoggedUser(): User?
    suspend fun registerUser(user: User)
    suspend fun login(user: AuthRequest)
    suspend fun logout()
}