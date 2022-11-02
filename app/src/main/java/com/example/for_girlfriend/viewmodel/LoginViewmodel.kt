package com.example.for_girlfriend.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.for_girlfriend.viewmodel.repo.LoginCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginViewmodel : ViewModel(){
    private val loginRepo = LoginCheck()
    val loginChk = MutableLiveData<Boolean>()
    val emailChk = MutableLiveData<String>()
    val passChk = MutableLiveData<String>()

    suspend fun isLogin() {
        var id = emailChk.value
        var pass = passChk.value
        loginChk.value = withContext(Dispatchers.IO) {
            loginRepo.loginIsNull(id, pass)
        }?:false
    }
}