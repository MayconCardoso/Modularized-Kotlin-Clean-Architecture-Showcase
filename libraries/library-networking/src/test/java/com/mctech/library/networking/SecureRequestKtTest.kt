package com.mctech.library.networking

import kotlinx.coroutines.runBlocking
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test

/**
 * @author MAYCON CARDOSO on 2019-09-28.
 */
class SecureRequestKtTest {

    @Test
    fun `should return a known network error`() {
        runBlocking {

            val result = runCatching {
                secureRequest<Int>(suspend {
                    throw Throwable()
                })
            }.exceptionOrNull()

            assertThat(result)
                .isExactlyInstanceOf(
                    NetworkError.UnknownNetworkingError::class.java
                )
        }
    }
}