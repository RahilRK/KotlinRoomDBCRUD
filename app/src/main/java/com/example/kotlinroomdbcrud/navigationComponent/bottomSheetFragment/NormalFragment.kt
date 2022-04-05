package com.example.kotlinroomdbcrud.navigationComponent.bottomSheetFragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.FragmentNormalBinding

class NormalFragment : Fragment() {

    var TAG = this.javaClass.simpleName

    lateinit var binding: FragmentNormalBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNormalBinding.inflate(layoutInflater,container,false)

        binding.openBottomSheetBt.setOnClickListener {

            findNavController().navigate(R.id.action_normalFragment_to_bottomSheetFragment)
        }

        findNavController().navigate(R.id.action_normalFragment_to_bottomSheetFragment)

        return binding.root
    }
}