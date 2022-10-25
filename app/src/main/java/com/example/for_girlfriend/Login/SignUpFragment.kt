package com.example.for_girlfriend.Login

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.app.ProgressDialog.show
import android.content.DialogInterface
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.Base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.Viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentSignUpBinding
import java.util.*


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {
    private val loginViewmodel: LoginViewmodel by activityViewModels()
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnCal.setOnClickListener {
            val cal = Calendar.getInstance()
            val dateSetListener = DatePickerDialog.OnDateSetListener{view,year,month,dayOfMonth->
                var datestring = "${year}년 ${month+1}월 ${dayOfMonth}일"
                binding.btnCal.text = datestring
        }
            DatePickerDialog(requireContext(), dateSetListener, cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DAY_OF_MONTH)).show()
        }
        binding.btnSignup.setOnClickListener{
            Log.d("되는중","되는중임")
            if (loginViewmodel.passChk(binding.etPass.text.toString(),binding.etPassCheck.text.toString())){
                if(loginViewmodel.isSignUp(binding.etEmail.text.toString(),binding.etPass.text.toString())){
                    Toast.makeText(requireContext(),"로그인 성공",Toast.LENGTH_SHORT)
                    findNavController().navigate(R.id.action_signUpFragment_to_loginFragment)
                }
                else{
                    val dlg:AlertDialog.Builder = AlertDialog.Builder(requireContext())
                        .setTitle("로그인 실패")
                        .setMessage("아이디와 비밀번호를 다시 확인 해주세요.")
                        .setPositiveButton("확인"){ dialog, id -> binding.etEmail.requestFocus()}
                    dlg.show()
                }
            }
            else{
                val dlg:AlertDialog.Builder = AlertDialog.Builder(requireContext())
                    .setTitle("로그인 실패")
                    .setMessage("비밀번호가 서로 다릅니다. 다시 설정해 주세요.")
                    .setPositiveButton("확인"){ dialog, id -> binding.etPass.requestFocus()}
                dlg.show()
            }
        }
    }
}

