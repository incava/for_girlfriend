package com.example.for_girlfriend.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.HomeActivity
import com.example.for_girlfriend.MainActivity
import com.example.for_girlfriend.base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentLoginBinding
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull

class LoginFragment : BaseFragment<FragmentLoginBinding>(R.layout.fragment_login) {
    private val loginVM:LoginViewmodel by activityViewModels()
    private lateinit var callback: OnBackPressedCallback

    override fun onAttach(context: Context) {
        super.onAttach(context)
        var mBackWait: Long = 0
        callback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (System.currentTimeMillis() - mBackWait >= 2000) {
                    mBackWait = System.currentTimeMillis()
                    Toast.makeText(requireActivity(), "뒤로가기 버튼을 한 번 더 누르면 종료합니다.", Toast.LENGTH_SHORT).show()
                } else {
                    requireActivity().finish()
                }
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(this, callback)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginViewmodel = loginVM
        binding.lifecycleOwner= viewLifecycleOwner
        setupObserver()
        setupClickListener()
}
    private fun setupObserver(){
        loginVM.loginChk.observe(viewLifecycleOwner) {
            if (it) {
                val intent = Intent(activity, HomeActivity::class.java)
                startActivity(intent)
            }//(activity as MainActivity).goHome()
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
            viewLifecycleOwner.lifecycleScope.launch {
                binding.pbLogin.visibility = View.VISIBLE
                binding.tvPb.visibility = View.VISIBLE
                val timeRequest = withTimeoutOrNull(10000) {
                        loginVM.isLogin()
                }
                if (timeRequest == null){
                    Toast.makeText(requireContext(),"네트워크 상태가 좋지 않습니다. 데이터 연결을 확인해 주세요.",Toast.LENGTH_SHORT).show()
                }
                binding.pbLogin.visibility = View.INVISIBLE
                binding.tvPb.visibility = View.INVISIBLE
            }
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback.remove()
    }
}


