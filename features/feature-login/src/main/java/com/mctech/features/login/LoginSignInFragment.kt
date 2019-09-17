package com.mctech.features.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.mctech.features.login.databinding.FragmentSignInBinding
import kotlinx.android.synthetic.main.fragment_sign_in.view.*
import org.koin.androidx.viewmodel.ext.android.sharedViewModel



class LoginSignInFragment : Fragment() {
    private val loginViewModel: LoginViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSignInBinding.inflate(inflater, container, false)
        val view = binding.getRoot()

        view.btSignUp.setOnClickListener{
            val navController = findNavController()
            navController.navigate(R.id.action_loginFormFragment_to_loginSignUpFragment)
        }

        return view
    }
}
