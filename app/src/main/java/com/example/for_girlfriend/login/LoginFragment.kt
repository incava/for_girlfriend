package com.example.for_girlfriend.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val loginVM:LoginViewmodel by activityViewModels()
//    override fun init() {
//
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginViewmodel = loginVM
        binding.lifecycleOwner= this.viewLifecycleOwner
        setupObserver()
        setupClickListener()
        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
}
    fun setupObserver(){
        loginVM.loginChk.observe(viewLifecycleOwner) {
            if (it) findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            else {
                Toast.makeText(this.context, "등록이 실패하였습니다.", Toast.LENGTH_SHORT)
            }
        }
    }


    fun setupClickListener(){
        binding.btnLogin.setOnClickListener{
           loginVM.isLogin()
        }
    }


    }

