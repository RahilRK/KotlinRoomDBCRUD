package com.example.kotlinroomdbcrud.navigationComponent.IntroScreens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.FragmentFirstScreenBinding
import com.example.kotlinroomdbcrud.databinding.FragmentSecondScreenBinding
import com.example.kotlinroomdbcrud.databinding.FragmentThirdScreenBinding

class ThirdScreen : Fragment() {

    val TAG = this.javaClass.simpleName

    lateinit var binding: FragmentThirdScreenBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentThirdScreenBinding.inflate(layoutInflater, container, false)

        binding.finishBt.setOnClickListener {


            findNavController().navigate(R.id.action_viewPagerFragment_to_homeFragment2)
        }

        return binding.root
    }
}