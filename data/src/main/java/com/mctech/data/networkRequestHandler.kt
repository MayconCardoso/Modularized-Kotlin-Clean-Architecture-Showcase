package com.mctech.data

import com.mctech.domain.errors.NetworkException
import com.mctech.library.networking.secureRequest


// Called just to handle a secure request returning the response or throwing an error known by the app.
suspend fun <T> networkRequestHandler(target: suspend () -> T) = try {
    // Call method who map all networking exception when it happen
    secureRequest(target)
} catch (error: Exception) {
    throw NetworkException()
}

suspend fun <T> networkRequestSilentErrorHandler(target: suspend () -> T) = try {
    // Call method who map all networking exception when it happen
    secureRequest(target)
} catch (error: Exception) {
    error.printStackTrace()
    null
}