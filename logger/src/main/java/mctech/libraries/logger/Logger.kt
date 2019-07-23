package mctech.libraries.logger

/**
 * @author MAYCON CARDOSO on 2019-07-22.
 */
interface Logger {
    fun v(message: String)
    fun d(message: String)
    fun i(message: String)
    fun w(message: String)
    fun e(message: String)
    fun e(e: Throwable)
}