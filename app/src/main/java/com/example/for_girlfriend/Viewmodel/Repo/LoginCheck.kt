package com.example.for_girlfriend.Viewmodel.Repo

import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class LoginCheck{
    val mAuth = Firebase.auth
    private val _getUser = MutableLiveData<String?>()
    val getUser: LiveData<String?> get() = _getUser
    fun isLoginRepo(email: String, password: String):Boolean{
        var result = false
            mAuth.signInWithEmailAndPassword(email.trim(), password)
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
    fun signUp(email: String, password:String):Boolean{
        var flag = false
        mAuth.createUserWithEmailAndPassword(email, password)
            .addOnCompleteListener{ task ->
                if (task.isSuccessful) {
                    Log.d(TAG, "createUserWithEmail:success")
                    flag = true
                } else {
                    Log.w(TAG, "createUserWithEmail:failure", task.exception)
                    flag = false
                }
            }
        return flag
    }

}