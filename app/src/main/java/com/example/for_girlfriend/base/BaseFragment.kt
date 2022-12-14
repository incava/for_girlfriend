package com.example.for_girlfriend.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment

abstract class BaseFragment <B: ViewDataBinding>(@LayoutRes private val layoutResId: Int) : Fragment(){
    private var _binding : B? = null
    val binding get() = _binding ?: error("BaseFragment 에러")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //init()
        _binding = DataBindingUtil.inflate(inflater, layoutResId, container, false)
        return binding.root
    }

    //abstract fun init()
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}