package com.mctech.library.networking

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * This method helps the application to avoid unexpected crashes during some network request.
 * Basically, if there is any issue on the request, we can transform the error into
 * another one that the app know about.
 */
suspend fun <T> secureRequest(target: suspend () -> T): T = withContext(Dispatchers.IO){
    try {
        target.invoke()
    } catch (incoming: Throwable) {
        throw NetworkErrorTransformer.transform(incoming)
    }
}