package com.example.for_girlfriend.viewmodel.repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.*
import kotlinx.coroutines.tasks.asTask
import kotlin.coroutines.resume

class LoginCheck{
    val mAuth = Firebase.auth
    private val _getUser = MutableLiveData<String?>()
    val getUser: LiveData<String?> get() = _getUser
    private suspend fun isLoginRepo(email: String, password: String):Boolean = suspendCancellableCoroutine{ cont->
            mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                        Log.d(TAG, "$task")
                        val user = mAuth.currentUser.toString()
                        cont.resume(task.isSuccessful)
                        updateUI(user)
                }
    }

    private fun updateUI(userID: String?){
        _getUser.value = userID
    }

    private suspend fun isSignUpRepo(name:String,birth:String,email:String,pass:String,cFpass:String): Boolean =
        suspendCancellableCoroutine { cont ->
            mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener { task ->
                    cont.resume(task.isSuccessful)
                    //todo name birth,email,uid저장
                }
        }


    suspend fun signIsNull(name:String?,birth:String?,email:String?,pass:String?,cFpass:String?):Boolean{
        Log.d("cFpass!=pass","${(cFpass!=pass)}")
    return if (name.isNullOrBlank() || email.isNullOrBlank() || pass.isNullOrBlank() || birth.isNullOrBlank() || cFpass.isNullOrBlank() || cFpass!=pass)
        false
    else isSignUpRepo(name!!,birth!!,email!!,pass!!,cFpass!!)
    }
    suspend fun loginIsNull(email:String?, pass:String?):Boolean{
        return if (email.isNullOrBlank() && pass.isNullOrBlank())
            false
        else isLoginRepo(email!!,pass!!)
    }



}