package com.mctech.library.networking

import retrofit2.HttpException
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * @author MAYCON CARDOSO on 2019-09-28.
 *
 * Transform any exception into a NetworkError in order to avoid any crash on the app.
 */
object NetworkErrorTransformer {
    fun transform(incoming: Throwable) = when (incoming) {
        is HttpException -> {
            translateHttpExceptionUsingStatusCode(incoming.code())
        }
        is SocketTimeoutException -> {
            NetworkError.OperationTimeout
        }
        is UnknownHostException,
        is ConnectException,
        is NoRouteToHostException -> {
            NetworkError.HostUnreachable
        }
        else -> {
            resolveOtherException(incoming)
        }
    }

    private fun resolveOtherException(incoming: Throwable) = if(isRequestCanceled(incoming)){
        NetworkError.ConnectionSpike
    } else{
        NetworkError.UnknownNetworkingError
    }

    private fun isRequestCanceled(throwable: Throwable) =
        throwable is IOException &&
                throwable.message?.contentEquals("Canceled") ?: false

    private fun translateHttpExceptionUsingStatusCode(code: Int) =
        when (code) {
            in 400..499 -> NetworkError.ClientException
            else -> NetworkError.RemoteException
        }
}