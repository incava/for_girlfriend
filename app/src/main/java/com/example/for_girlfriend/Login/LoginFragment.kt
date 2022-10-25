package com.example.for_girlfriend.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.Base.BaseFragment
import com.example.for_girlfriend.Base.BaseNoDataFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.Viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentLoginBinding

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val loginViewmodel:LoginViewmodel by activityViewModels()
//    override fun init() {
//
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener{
            if (loginViewmodel.isLogin(binding.etEmail.text.toString(),binding.etPassword.text.toString())){
                findNavController().navigate(R.id.action_loginFragment_to_homeFragment)
            }
            else{
                Toast.makeText(this.context,"등록이 실패하였습니다.",Toast.LENGTH_SHORT)
            }
        }
        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
    }
}

