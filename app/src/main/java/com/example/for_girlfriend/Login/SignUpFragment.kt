package com.example.for_girlfriend.Login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.for_girlfriend.Base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.Viewmodel.LoginViewmodel
import com.example.for_girlfriend.databinding.FragmentSignUpBinding


class SignUpFragment : BaseFragment<FragmentSignUpBinding>(R.layout.fragment_sign_up) {
    private val loginViewmodel: LoginViewmodel by activityViewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.calendarView.visibility = View.INVISIBLE
        binding.btnCal.setOnClickListener {
            binding.calendarView.visibility = View.VISIBLE
            binding.calendarView.bringToFront()
        }
        binding.calendarView.setOnDateChangeListener { view, year, month, day ->
            binding.btnCal.text = "${year}년${month}월${day}일"
            binding.calendarView.visibility = View.INVISIBLE
        }
    }
}
