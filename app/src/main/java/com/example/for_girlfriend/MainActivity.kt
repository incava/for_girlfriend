package com.example.for_girlfriend

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.for_girlfriend.databinding.ActivityMainBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val currentUser = Firebase.auth.currentUser
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Log.d("currentUser",currentUser.toString())
//        if (currentUser!=null){
//            Log.d("실행중","실행중")
//            goHome()
//        }
    }
     fun goHome(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}