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
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentSignUpBinding
import com.example.for_girlfriend.viewmodel.SignupViewmodel
import java.util.*


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {
    private val signUpVm :SignupViewmodel by activityViewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signupVM=signUpVm
        binding.lifecycleOwner=viewLifecycleOwner
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
    fun setupObserver(){
        signUpVm.signUpChk.observe(viewLifecycleOwner) {
            Log.d("observer","$it")
            if (it) {
                Toast.makeText(this.context, "로그인 성공", Toast.LENGTH_SHORT)
                findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
            }
            else {
                val dlg: AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    .setTitle("로그인 실패")
                    .setMessage("아이디와 비밀번호를 다시 확인 해주세요.")
                    .setPositiveButton("확인"){ dialog, id -> binding.etEmail.requestFocus()}
                dlg.show()
            }
        }
    }

    fun setupClickListener(){
        binding.btnSignup.setOnClickListener{
            signUpVm.isSignUp()
        }
    }
}

