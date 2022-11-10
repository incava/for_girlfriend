package com.example.for_girlfriend.menu

import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.example.for_girlfriend.base.BaseFragment
import com.example.for_girlfriend.R
import com.example.for_girlfriend.databinding.FragmentHomeBinding
import com.google.android.material.tabs.TabLayoutMediator


class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.loginViewmodel = loginVM
        binding.lifecycleOwner= viewLifecycleOwner
        initViewPager()
    }
    private fun initViewPager() {
        var viewPagerAdapter = ViewPagerAdapter(this)
        viewPagerAdapter.addFragment(CalenderViewFragment())
        viewPagerAdapter.addFragment(BucketListFragment())
        viewPagerAdapter.addFragment(PictureFramgment())
        viewPagerAdapter.addFragment(CapsuleDiaryFragment())
        val tabTitleArray = arrayOf(
            "캘린더",
            "버킷리스트",
            "사진공유",
            "캡슐 일기"
        )
        binding.vpHome.apply {
            adapter = viewPagerAdapter
            registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
                override fun onPageSelected(position: Int) {
                    //쓸일이 없어서 일단 놔둠.
                    super.onPageSelected(position)
                }
            })
        }
        TabLayoutMediator(binding.tabLayoutHome,binding.vpHome){tab,position ->
            tab.text = tabTitleArray[position]
        }.attach()
    }
}