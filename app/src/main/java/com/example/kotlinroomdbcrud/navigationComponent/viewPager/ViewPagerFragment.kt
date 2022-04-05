package com.example.kotlinroomdbcrud.navigationComponent.viewPager

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.FragmentViewPagerBinding
import com.example.kotlinroomdbcrud.navigationComponent.IntroScreens.FirstScreen
import com.example.kotlinroomdbcrud.navigationComponent.IntroScreens.SecondScreen
import com.example.kotlinroomdbcrud.navigationComponent.IntroScreens.ThirdScreen

class ViewPagerFragment : Fragment() {

    val TAG = this.javaClass.simpleName

    lateinit var binding: FragmentViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentViewPagerBinding.inflate(layoutInflater, container, false)

        val fragmentList = arrayListOf<Fragment>(
            FirstScreen(),
            SecondScreen(),
            ThirdScreen()
        )

        val adapter = viewPagerAdapter(
            fragmentList,
            requireActivity().supportFragmentManager,
            lifecycle)

        binding.viewPager.adapter = adapter

        return binding.root
    }
}