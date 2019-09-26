package com.mctech.libraries.logger


/**
 * @author MAYCON CARDOSO on 2019-07-22.
 */
class MutedLogger : Logger {
    override fun v(message: String) = Unit
    override fun d(message: String) = Unit
    override fun i(message: String) = Unit
    override fun w(message: String) = Unit
    override fun e(message: String) = Unit
    override fun e(e: Throwable) = Unit
}