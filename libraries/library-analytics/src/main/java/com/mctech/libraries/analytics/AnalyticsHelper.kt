package com.mctech.libraries.analytics

import android.app.Activity

/**
 * @author MAYCON CARDOSO on 2019-07-24.
 */
interface AnalyticsHelper{
    fun sendScreenView(screenName: String, activity: Activity)
    fun logUiEvent(itemId: String, action: String)
    fun setUserSignedIn(isSignedIn: Boolean)
    fun setUserRegistered(isRegistered: Boolean)
}