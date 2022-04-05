package com.example.kotlinroomdbcrud.navigationComponent.IntroScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.FragmentFirstScreenBinding
import com.example.kotlinroomdbcrud.databinding.FragmentViewPagerBinding

class FirstScreen : Fragment() {

    val TAG = this.javaClass.simpleName

    lateinit var binding: FragmentFirstScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstScreenBinding.inflate(layoutInflater, container, false)

        //todo calling viewPager of Activity from Fragment
        val viewPager = activity?.findViewById<ViewPager2>(R.id.viewPager)
        binding.nextBt.setOnClickListener {

            //todo Go to Second Screen
            viewPager?.currentItem = 1
        }

        return binding.root
    }
}