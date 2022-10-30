package com.example.for_girlfriend.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.for_girlfriend.viewmodel.repo.LoginCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class SignupViewmodel : ViewModel() {

        private val loginRepo = LoginCheck()
        val signUpChk = MutableLiveData<Boolean>()
        val name = MutableLiveData("")
        val email = MutableLiveData("")
        val pass = MutableLiveData("")
        val birth = MutableLiveData("선택하세요")
        val cFpass = MutableLiveData("")

        fun isSignUp() {
                val name = this.name.value
                val id = email.value
                val password = pass.value
                val birth = this.birth.value
                val cFpass = this.cFpass.value
                Log.d("check", "$name $id $password $birth $cFpass")
                signUpChk.value = loginRepo.signIsNull(name, id, password, birth, cFpass)
        }
}