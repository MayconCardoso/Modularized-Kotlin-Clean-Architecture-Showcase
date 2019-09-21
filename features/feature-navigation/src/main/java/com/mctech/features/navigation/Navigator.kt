package com.mctech.features.navigation

import android.content.Intent
import androidx.fragment.app.FragmentActivity

class Navigator(
    private val host: FragmentActivity,
    private val links: Map<Screen, Class<FragmentActivity>>
) {
    fun navigateTo(destination: Screen, finishHost : Boolean = false) {
        val next = Intent(host, find(destination))
        host.startActivity(next)

        if(finishHost) host.finish()
    }

    private fun find(target: Screen) =
        links[target]
            ?.let { it }
            ?: throw UnsupportedNavigation(target)
}