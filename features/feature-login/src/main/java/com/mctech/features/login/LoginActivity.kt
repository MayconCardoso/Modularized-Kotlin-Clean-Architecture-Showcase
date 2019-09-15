package com.mctech.features.login

import android.os.Bundle
import com.mctech.feature.arq.BaseActivity

class LoginActivity : BaseActivity<LoginViewModel>() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
    }
}
