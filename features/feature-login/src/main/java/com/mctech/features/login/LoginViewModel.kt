package com.mctech.features.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.mctech.feature.arq.BaseViewModel
import com.mctech.features.login.state.LoginScreenState

class LoginViewModel() : BaseViewModel(){
    private val _loginSreenState = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
    val loginSreenState : LiveData<LoginScreenState>
        get() = _loginSreenState

}
