package com.mctech.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.mctech.domain.errors.AuthException
import com.mctech.domain.model.AuthRequest
import com.mctech.feature.arq.BaseFragment
import com.mctech.feature.arq.extentions.bindData
import com.mctech.feature.arq.extentions.enableByState
import com.mctech.feature.arq.extentions.getValue
import com.mctech.feature.arq.extentions.setVisibilityByState
import com.mctech.features.login.state.LoginState
import kotlinx.android.synthetic.main.fragment_sign_in.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginSignInFragment : BaseFragment<LoginViewModel>() {
    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = inflater.inflate(R.layout.fragment_sign_in, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData(loginViewModel.loginSreenState) { renderUi(it) }

        btSignIn?.setOnClickListener { tryLogin() }
        btSignUp?.setOnClickListener { navigateToSignUp() }
    }

    private fun tryLogin() {
        lifecycleScope.launch {
            loginViewModel.doLogin(
                AuthRequest(
                    email = etUsername.getValue(),
                    password = etPassword.getValue()
                )
            )
        }
    }

    private fun navigateToSignUp() {
        loginViewModel.navigationToSignUp(
            AuthRequest(
                email = etUsername.getValue(),
                password = etPassword.getValue()
            )
        )

        val navController = findNavController()
        navController.navigate(R.id.action_loginFormFragment_to_loginSignUpFragment)
    }

    private fun renderUi(loginState: LoginState) {
        when (loginState) {
            is LoginState.Unauthenticated -> {
                switchState(isLoading = false)
            }
            is LoginState.Loading -> {
                switchState(isLoading = true)
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

    private fun showError(error: AuthException) {
        val errorMessage = when (error) {
            is AuthException.EmptyFormValueException -> R.string.auth_form_empty
            is AuthException.UserNotFoundException -> R.string.auth_user_not_found
            is AuthException.InvalidEmailFormatException -> R.string.auth_email_bad_format
            is AuthException.PasswordUnderFiveCharactersException -> R.string.auth_invalid_password
            is AuthException.WrongCredentialsException -> R.string.auth_wrong_credentials
            else -> R.string.auth_unknown_error
        }
        Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
    }
}