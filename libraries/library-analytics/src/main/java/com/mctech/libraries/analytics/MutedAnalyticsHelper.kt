package com.mctech.libraries.analytics

import android.app.Activity

/**
 * @author MAYCON CARDOSO on 2019-07-24.
 */
class MutedAnalyticsHelper : AnalyticsHelper {
    override fun sendScreenView(screenName: String, activity: Activity) {}
    override fun logUiEvent(itemId: String, action: String) {}
    override fun setUserSignedIn(isSignedIn: Boolean) {}
    override fun setUserRegistered(isRegistered: Boolean) {}
}