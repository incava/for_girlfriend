package com.example.for_girlfriend.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.example.for_girlfriend.R

abstract class BaseActivity<T : ViewDataBinding>(@LayoutRes val layoutResID: Int) : AppCompatActivity() {
    lateinit var viewDataBinding: T
    abstract val viewModel: R //아직 viewmodel에 대해 coroutine을 사용하지 못해 Base는 만들지 않음.
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewDataBinding = DataBindingUtil.setContentView(this, layoutResID)
        viewDataBinding.lifecycleOwner = this@BaseActivity
    }
}