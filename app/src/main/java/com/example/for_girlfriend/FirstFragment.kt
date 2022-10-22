package com.example.for_girlfriend

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.for_girlfriend.Base.BaseFragment
import com.example.for_girlfriend.Base.BaseNoDataFragment
import com.example.for_girlfriend.databinding.FragmentFirstBinding
import com.example.for_girlfriend.databinding.FragmentHomeBinding
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class FirstFragment : BaseNoDataFragment<FragmentFirstBinding>(){
    override fun onStart() {
        super.onStart()
        val currentUser = Firebase.auth.currentUser
        Log.d("currentUser",currentUser.toString())
        if (currentUser==null){
            findNavController().navigate(R.id.action_firstFragment_to_loginFragment)
        }
        else{
            findNavController().navigate(R.id.action_firstFragment_to_homeFragment)
        }
    }
    override fun getFragmentBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentFirstBinding {
        return  FragmentFirstBinding.inflate(inflater,container,false)
    }
}