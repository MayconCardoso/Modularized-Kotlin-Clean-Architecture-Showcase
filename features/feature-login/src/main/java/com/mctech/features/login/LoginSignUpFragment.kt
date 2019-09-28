package com.mctech.features.login

import android.os.Bundle
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.mctech.domain.model.AuthRequest
import com.mctech.domain.model.RegisterUser
import com.mctech.domain.model.User
import com.mctech.feature.arq.BaseFragment
import com.mctech.feature.arq.extentions.*
import com.mctech.features.login.interaction.LoginUserInteraction
import com.mctech.features.login.state.LoginState
import com.mctech.features.login.state.toStateResource
import kotlinx.android.synthetic.main.fragment_sign_up.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class LoginSignUpFragment : BaseFragment<LoginViewModel>() {
    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun getLayoutId() = R.layout.fragment_sign_up

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bindData(loginViewModel.loginScreenState) { renderUi(it) }
        bindData(loginViewModel.lastAuthRequest) { autoFillUpForm(it) }
        btSignUp?.setOnClickListener { tryRegisterUser() }
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
                toast(loginState.toStateResource().message)
            }
        }
    }

    private fun tryRegisterUser() {
        lifecycleScope.launch {
            loginViewModel.suspendedInteraction(
                LoginUserInteraction.TryRegisterUser(
                    RegisterUser(
                        user = User(
                            name = etName.getValue(),
                            email = etUsername.getValue()
                        ),
                        password = etPassword.getValue(),
                        passwordConfirmation = etConfirmPassword.getValue()
                    )
                )
            )
        }
    }

    private fun autoFillUpForm(request: AuthRequest) {
        etUsername.setText(request.email)
        etPassword.setText(request.password)
        etConfirmPassword.setText(request.password)
    }

    private fun setFormState(enabled: Boolean) {
        etName.enableByState(enabled)
        etUsername.enableByState(enabled)
        etPassword.enableByState(enabled)
        etConfirmPassword.enableByState(enabled)
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
