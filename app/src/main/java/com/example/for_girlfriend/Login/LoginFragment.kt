package com.example.for_girlfriend.Login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.for_girlfriend.Base.BaseNoDataFragment
import com.example.for_girlfriend.Viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentLoginBinding

class LoginFragment : BaseNoDataFragment<FragmentLoginBinding>() {
    private lateinit var viewmodel: LoginViewmodel
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLoginBinding {
        return FragmentLoginBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnLogin.setOnClickListener {
            viewmodel.
        }
    }





}
