package com.mctech.features.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mctech.domain.model.AuthRequest
import com.mctech.feature.arq.extentions.bindData
import com.mctech.feature.arq.extentions.enableByState
import com.mctech.feature.arq.extentions.getValue
import com.mctech.feature.arq.extentions.setVisibilityByState
import com.mctech.features.login.state.LoginState
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginSignInFragment : BaseLoginFragment() {
    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun getLayoutId() = R.layout.fragment_sign_in

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData(loginViewModel.loginSreenState) { renderUi(it) }

        btSignIn?.setOnClickListener { tryLogin() }
        btSignUp?.setOnClickListener { navigateToSignUp() }
    }

    private fun tryLogin() {
        lifecycleScope.launch {
            loginViewModel.doLogin( createAuthRequest() )
        }
    }

    private fun navigateToSignUp() {
        loginViewModel.navigationToSignUp( createAuthRequest() )

        val navController = findNavController()
        navController.navigate(R.id.action_loginFormFragment_to_loginSignUpFragment)
    }

    private fun createAuthRequest() = AuthRequest(
        email = etUsername.getValue(),
        password = etPassword.getValue()
    )

    private fun renderUi(loginState: LoginState) {
        when (loginState) {
            is LoginState.Loading -> {
                switchState(isLoading = true)
            }
            is LoginState.Unauthenticated -> {
                switchState(isLoading = false)
            }
            is LoginState.Error -> {
                switchState(isLoading = false)
                showError(loginState.error)
            }
        }
    }

    private fun setFormState(enabled: Boolean) {
        etUsername.enableByState(enabled)
        etPassword.enableByState(enabled)
        btSignIn.setVisibilityByState(enabled)
        btSignUp.setVisibilityByState(enabled)
    }

    private fun setProgressState(showing: Boolean) {
        loadingProgress.setVisibilityByState(showing)
    }

    private fun switchState(isLoading: Boolean) {
        setProgressState(isLoading)
        setFormState(!isLoading)
    }
}