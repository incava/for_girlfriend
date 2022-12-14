package com.example.for_girlfriend.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.for_girlfriend.viewmodel.repo.LoginCheck
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class SignupViewmodel : ViewModel() {

        private val loginRepo = LoginCheck()
        val signUpChk = MutableLiveData<Boolean>()
        val name = MutableLiveData("")
        val email = MutableLiveData("")
        val pass = MutableLiveData("")
        val birth = MutableLiveData("선택하세요")
        val cFpass = MutableLiveData("")

        suspend fun isSignUp() {
                val name = this@SignupViewmodel.name.value
                val id = email.value
                val password = pass.value
                val birth = this@SignupViewmodel.birth.value
                val cFpass = this@SignupViewmodel.cFpass.value
                Log.d("check", "$name $id $password $birth $cFpass")
                signUpChk.value = withContext(Dispatchers.Main){
                        val a = loginRepo.signIsNull(name, birth, id, password, cFpass)
                        if (a) loginRepo.updateInfo(name!!,id!!,birth!!)
                        else false
                }?:false //이건 livedata boolean 버그이슈
        }
}