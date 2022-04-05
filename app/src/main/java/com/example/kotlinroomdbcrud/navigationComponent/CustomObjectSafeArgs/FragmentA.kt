package com.example.kotlinroomdbcrud.navigationComponent.CustomObjectSafeArgs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.FragmentABinding

class FragmentA : Fragment() {

    var TAG = this.javaClass.simpleName
    lateinit var binding: FragmentABinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentABinding.inflate(layoutInflater,container,false)
        binding.donebt.setOnClickListener {

            var getFirstName = binding.edFname.text.toString()
            var getLastName = binding.edLname.text.toString()
            var getAge = binding.edAge.text.toString().toInt()

            var model = profileModel(getFirstName,getLastName,getAge)

            var action = FragmentADirections.actionFragmentAToFragmentB(model)
            findNavController().navigate(action)
        }

        return binding.root
    }
}