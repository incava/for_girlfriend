package com.example.for_girlfriend.login

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentSignUpBinding
import com.example.for_girlfriend.viewmodel.SignupViewmodel
import kotlinx.coroutines.launch
import kotlinx.coroutines.withTimeoutOrNull
import java.util.*


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {
    private val signUpVm :SignupViewmodel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupVM = signUpVm
        binding.lifecycleOwner = viewLifecycleOwner
        setupObserver()
        setupClickListener()
        binding.btnCal.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener{view,year,month,dayOfMonth->
                var datestring = "${year}년 ${month+1}월 ${dayOfMonth}일"
                binding.btnCal.text = datestring
        }
            DatePickerDialog(requireContext(), dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    private fun setupObserver(){
        signUpVm.signUpChk.observe(viewLifecycleOwner) {
            Log.d("observer","$it")
            if (it) {
                Toast.makeText(this.context, "회원가입 성공", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }
            else {
                val dlg: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    .setTitle("로그인 실패")
                    .setMessage("아이디와 비밀번호를 다시 확인 해주세요.")
                    .setPositiveButton("확인"){ _, _ -> binding.etEmail.requestFocus()}
                dlg.show()
            }
        }
    }

    private fun setupClickListener(){
        binding.btnSignup.setOnClickListener{
            binding.pbLogin.visibility = View.VISIBLE
            binding.tvPb.visibility = View.VISIBLE
            viewLifecycleOwner.lifecycleScope.launch {
                val timeRequest = withTimeoutOrNull(10000) {
                    signUpVm.isSignUp()
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

