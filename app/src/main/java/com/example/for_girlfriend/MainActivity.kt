package com.example.for_girlfriend

import android.content.Intent
import android.os.Build
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

//    override fun finish() { // 다 죽일 필요는 없다 본다. 그래서 남겨둔다. 언젠간 쓸지도.
//            if (Build.VERSION.SDK_INT >= 21) {
//                // 액티비티 종료 + 태스크 리스트에서 지우기
//                finishAndRemoveTask();
//            } else {
//                // 액티비티 종료
//                finish();
//            }
//            System.exit(0);
//        super.finish()
//    }

     fun goHome(){
        val intent = Intent(this,HomeActivity::class.java)
        startActivity(intent)
    }
}