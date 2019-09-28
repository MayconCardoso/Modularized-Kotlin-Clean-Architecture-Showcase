package com.mctech.library.networking

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.assertj.core.api.Assertions
import org.junit.Test
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException
import java.net.ConnectException
import java.net.NoRouteToHostException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * @author MAYCON CARDOSO on 2019-09-28.
 */

class NetworkErrorTransformerTest {

    @Test
    fun `should return a client exception`() {
        internalAssertion(
            exception = createHttpException(400, "Bad format request"),
            expectedValue =  NetworkError.ClientException::class.java
        )

        internalAssertion(
            exception = createHttpException(404, "Not found"),
            expectedValue =  NetworkError.ClientException::class.java
        )
    }

    @Test
    fun `should return a server exception`() {
        internalAssertion(
            exception = createHttpException(500, "Internal server error"),
            expectedValue =  NetworkError.RemoteException::class.java
        )
    }

    @Test
    fun `should return a operation timeout`() {
        internalAssertion(
            exception = SocketTimeoutException(),
            expectedValue =  NetworkError.OperationTimeout::class.java
        )
    }

    @Test
    fun `should return a host unreachable`() {
        internalAssertion(
            exception = UnknownHostException(),
            expectedValue =  NetworkError.HostUnreachable::class.java
        )
        internalAssertion(
            exception = ConnectException(),
            expectedValue =  NetworkError.HostUnreachable::class.java
        )
        internalAssertion(
            exception = NoRouteToHostException(),
            expectedValue =  NetworkError.HostUnreachable::class.java
        )
    }

    @Test
    fun `should return a connection spike`() {
        internalAssertion(
            exception = IOException("Canceled"),
            expectedValue =  NetworkError.ConnectionSpike::class.java
        )
    }

    @Test
    fun `should return a default exception`() {
        internalAssertion(
            exception = Throwable(),
            expectedValue =  NetworkError.UnknownNetworkingError::class.java
        )
    }

    private fun internalAssertion(exception : Throwable, expectedValue : Class<*>){
        val result      = NetworkErrorTransformer.transform(exception)
        Assertions.assertThat(result)
            .isExactlyInstanceOf(
                expectedValue
            )
    }

    private fun createHttpException(code: Int, error: String): HttpException {
        val format = "application/json".toMediaTypeOrNull()
        val responseBody = error.toResponseBody(format)
        return HttpException(Response.error<Any>(code, responseBody))
    }
}