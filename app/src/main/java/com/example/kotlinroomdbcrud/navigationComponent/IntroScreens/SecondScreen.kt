package com.example.kotlinroomdbcrud.navigationComponent.IntroScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.FragmentFirstScreenBinding
import com.example.kotlinroomdbcrud.databinding.FragmentSecondScreenBinding

class SecondScreen : Fragment() {

    val TAG = this.javaClass.simpleName

    lateinit var binding: FragmentSecondScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSecondScreenBinding.inflate(layoutInflater, container, false)

        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)

        binding.nextBt.setOnClickListener {

            viewPager?.currentItem = 2
        }

        return binding.root
    }
}