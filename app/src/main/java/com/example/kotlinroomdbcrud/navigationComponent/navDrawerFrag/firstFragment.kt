package com.example.kotlinroomdbcrud.navigationComponent.navDrawerFrag

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.example.kotlinroomdbcrud.databinding.FragmentFirstBinding

class firstFragment : Fragment() {

    var TAG = this.javaClass.simpleName
    lateinit var binding : FragmentFirstBinding

    val args: firstFragmentArgs by navArgs()

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = FragmentFirstBinding.inflate(layoutInflater,container,false)

        binding.textViewOne.text = args.numberFirst.toString()
        binding.textViewOne.setOnClickListener {

            val action = firstFragmentDirections.navToSecondFragment(5)
            Navigation.findNavController(binding.root).navigate(action)
        }
        return binding.root
    }
}