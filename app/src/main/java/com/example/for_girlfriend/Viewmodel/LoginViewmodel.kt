package com.example.for_girlfriend.Viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.for_girlfriend.Viewmodel.Repo.LoginCheck
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewmodel : ViewModel(){
    private val loginCheck = LoginCheck()
    fun isLogin(id:String, pass:String):Boolean{
        Log.d("id",id)
        Log.d("pass",pass)
        return loginCheck.isLoginRepo(id, pass)
    }
    fun isSignUp(id:String, pass:String):Boolean{
        Log.d("id",id)
        Log.d("pass",pass)
        return loginCheck.isLoginRepo(id, pass)
    }

}