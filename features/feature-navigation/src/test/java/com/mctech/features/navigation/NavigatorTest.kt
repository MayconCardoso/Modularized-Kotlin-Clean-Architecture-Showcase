package com.mctech.features.navigation

import android.content.Intent
import androidx.fragment.app.FragmentActivity
import com.nhaarman.mockitokotlin2.argumentCaptor
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class NavigatorTest{
    private lateinit var navigator: Navigator
    private val mockActivity = mock<FragmentActivity>()
    private val links = mapOf<Screen, Class<FragmentActivity>>(
        Screen.Login to FragmentActivity::class.java
    )

    @Before
    fun `before each test`() {
        navigator = Navigator(mockActivity, links)
    }

    @Test
    fun `should navigate to supported screen`() {
        navigator.navigateTo(Screen.Login)
        argumentCaptor<Intent>().apply {
            verify(mockActivity).startActivity(capture())
            assertThat(firstValue).isNotNull()
        }
    }

    @Test fun `should throw when navigating to unsupported screen`() {
        assertThatThrownBy { navigator.navigateTo(Screen.Splash) }
            .isEqualTo(
                UnsupportedNavigation(Screen.Splash)
            )
    }
}