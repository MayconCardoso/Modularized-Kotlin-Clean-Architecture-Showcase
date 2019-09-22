package com.mctech.features.login

import android.widget.Toast
import com.mctech.domain.errors.AuthException
import com.mctech.feature.arq.BaseFragment

/**
 * @author MAYCON CARDOSO on 2019-09-21.
 */
abstract class BaseLoginFragment : BaseFragment<LoginViewModel>(){
    protected fun showError(error: AuthException) {
        val errorMessage = when (error) {
            is AuthException.EmptyFormValueException -> R.string.auth_form_empty
            is AuthException.UserNotFoundException -> R.string.auth_user_not_found
            is AuthException.InvalidEmailFormatException -> R.string.auth_email_bad_format
            is AuthException.PasswordUnderFiveCharactersException -> R.string.auth_invalid_password
            is AuthException.WrongCredentialsException -> R.string.auth_wrong_credentials
            is AuthException.AlreadyRegisteredUserException -> R.string.auth_user_already_registered
            is AuthException.PasswordsDoNotMatchException -> R.string.auth_password_dont_match
            else -> R.string.auth_unknown_error
        }
        Toast.makeText(activity, errorMessage, Toast.LENGTH_LONG).show()
    }
}