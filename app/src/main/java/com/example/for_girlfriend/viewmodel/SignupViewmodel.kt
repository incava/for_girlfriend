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
                        //val signUpChk = MutableLiveData<Boolean>() 위에 선언되어있음.
                        signUpChk.value = withContext(Dispatchers.IO){ //여기를 io로 하면안됨... 이유가 무엇일까?
                        val a = loginRepo.signIsNull(name, birth, id, password, cFpass)
                        if (a) loginRepo.updateInfo(name!!,id!!,birth!!)
                        else a
                }?:false //이건 livedata boolean 버그이슈
        }
}