package com.example.kotlinroomdbcrud.navigationComponent.CustomObjectSafeArgs

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.FragmentABinding
import com.example.kotlinroomdbcrud.databinding.FragmentBBinding

class FragmentB : Fragment() {

    var TAG = this.javaClass.simpleName
    lateinit var binding: FragmentBBinding

    val args by navArgs<FragmentBArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentBBinding.inflate(layoutInflater,container,false)

        binding.tvData.text =
            "First Name: ${args.profileModel.firstName}\n"+
                    "Last Name: ${args.profileModel.lastName}\n"+
                    "Age: ${args.profileModel.age}"

        return binding.root
    }
}