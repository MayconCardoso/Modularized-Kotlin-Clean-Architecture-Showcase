package com.mctech.kotlinlearning

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mctech.features.login.LoginActivity
import com.mctech.features.onboarding.OnboardingActivity

/**
 * @author MAYCON CARDOSO on 2019-09-10.
 */
class TesteActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onCLickLogar(view: View) {

        startActivity(Intent(this, LoginActivity::class.java))
    }

    fun onBoard(view: View) {
        startActivity(Intent(this, OnboardingActivity::class.java))
    }
}