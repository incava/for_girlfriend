package com.example.for_girlfriend.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.for_girlfriend.viewmodel.repo.LoginCheck

class LoginViewmodel : ViewModel(){
    private val loginRepo = LoginCheck()
    val loginChk = MutableLiveData<Boolean>()
    val emailChk = MutableLiveData<String>()
    val passChk = MutableLiveData<String>()

    fun isLogin(){
        var id = emailChk.value
        var pass = passChk.value
        Log.d("id,pass","$id $pass")
        loginChk.value = loginRepo.loginIsNull(id,pass)
        Log.d("id,pass","${loginChk.value.toString()}")
    }

}