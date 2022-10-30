package com.example.for_girlfriend.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseNoDataFragment <B: ViewBinding>: Fragment() {
    private var _binding: B? = null
    val binding get() = _binding ?: error("BaseFragment 에러")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getFragmentBinding(inflater, container)
        // B를 상속받아 binding하려하니 abstract로 하라해서 함수로 추상화만 해둠. 어차피 상속할거라 괜찮다.
        return binding.root
    }

    abstract fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?): B

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}