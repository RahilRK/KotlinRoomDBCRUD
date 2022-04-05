package com.example.kotlinroomdbcrud.navigationComponent.navDrawerFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.kotlinroomdbcrud.databinding.FragmentSecondBinding

class secondFragment : Fragment() {

    var TAG = this.javaClass.simpleName
    lateinit var binding : FragmentSecondBinding

    val args : secondFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment

        binding = FragmentSecondBinding.inflate(layoutInflater,container,false)
        binding.textViewOne.text = args.numberSec.toString()
        binding.textViewOne.setOnClickListener {

            val action = secondFragmentDirections.navToFirstFragment(10)
            Navigation.findNavController(binding.root).navigate(action)
        }

        return binding.root
    }
}