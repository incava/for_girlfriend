package com.example.for_girlfriend.viewmodel.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.asTask

class LoginCheck{
    val mAuth = Firebase.auth
    private val _getUser = MutableLiveData<String?>()
    val getUser: LiveData<String?> get() = _getUser
    fun isLoginRepo(email: String, password: String):Boolean{
        var result = false
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        Log.d(TAG, "createUserWithEmail:success")
                        val user = mAuth.currentUser.toString()
                        updateUI(user)
                        result = true
                    } else {
                        Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        updateUI(null)
                    }
                }
        return result
    }

    private fun updateUI(userID: String?){
        _getUser.value = userID
    }

    fun isSignUpRepo(email: String, password:String): Boolean {
            var flags = false
                mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Log.d(TAG, "createUserWithEmail:success")
                            flags = true
                        } else {
                            Log.w(TAG, "createUserWithEmail:failure", task.exception)
                        }
                    }
        return flags
        //todo 비동기를 동기로 리턴해야함.
    }


    fun signIsNull(name:String?,email:String?,pass:String?,birth:String?,cFpass:String?):Boolean{
        Log.d("cFpass!=pass","${(cFpass!=pass)}")
    return if (name.isNullOrBlank() || email.isNullOrBlank() || pass.isNullOrBlank() || birth.isNullOrBlank() || cFpass.isNullOrBlank() || cFpass!=pass)
        false
    else isSignUpRepo(email!!,pass!!)
    }
    fun loginIsNull(email:String?,pass:String?):Boolean{
        return if (email.isNullOrBlank() && pass.isNullOrBlank())
            false
        else isLoginRepo(email!!,pass!!)
    }



}