package com.example.for_girlfriend.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.MainActivity
import com.example.for_girlfriend.base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val loginVM:LoginViewmodel by activityViewModels()
//    override fun init() {
//
//    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginViewmodel = loginVM
        binding.lifecycleOwner= viewLifecycleOwner
        setupObserver()
        setupClickListener()
}
    private fun setupObserver(){
        loginVM.loginChk.observe(viewLifecycleOwner) {
            if (it) (activity as MainActivity).goHome()
            else {
                Toast.makeText(this.context, "등록이 실패하였습니다.", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun setupClickListener(){
        binding.btnSignUp.setOnClickListener{
            findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
        }
        binding.btnLogin.setOnClickListener{
            binding.pbLogin.visibility = View.VISIBLE
            binding.tvPb.visibility = View.VISIBLE
            viewLifecycleOwner.lifecycleScope.launch {
                val timeRequest = withTimeoutOrNull(10000) {
                        loginVM.isLogin()
                }
                if (timeRequest == null){
                    Toast.makeText(requireContext(),"네트워크 상태가 좋지 않습니다. 데이터 연결을 확인해 주세요.",Toast.LENGTH_SHORT).show()
                }
            }
            binding.pbLogin.visibility = View.INVISIBLE
            binding.tvPb.visibility = View.INVISIBLE
        }
    }
}

