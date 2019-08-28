package com.mctech.kotlinlearning.platform.logger

import android.util.Log
import mctech.libraries.logger.Logger

/**
 * @author MAYCON CARDOSO on 2019-07-22.
 */
internal object  LogcatLogger : Logger {
    const val TAG = "com.mctech.logger"

    override fun v(message: String) {
        Log.v(TAG, message)
    }

    override fun d(message: String) {
        Log.d(TAG, message)
    }

    override fun i(message: String) {
        Log.i(TAG, message)
    }

    override fun w(message: String) {
        Log.w(TAG, message)
    }

    override fun e(message: String) {
        Log.e(TAG, message)
    }

    override fun e(e: Throwable) {
        Log.e(TAG, e.message, e)
    }

}