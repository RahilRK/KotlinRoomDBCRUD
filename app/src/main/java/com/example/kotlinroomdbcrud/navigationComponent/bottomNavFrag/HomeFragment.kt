package com.example.kotlinroomdbcrud.navigationComponent.bottomNavFrag

import android.app.ProgressDialog
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.kotlinroomdbcrud.databinding.FragmentHomeBinding
import com.example.kotlinroomdbcrud.retrofitExample.retrofitRepository
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeFragment : Fragment() {

    var TAG = "HomeFragment"

    lateinit var binding: FragmentHomeBinding
//    lateinit var homeFragViewModel: HomeFragViewModel

//    val job = Job()
//    val ioScope = CoroutineScope(Dispatchers.IO + job)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(layoutInflater, container, false)

/*        val repository = retrofitRepository()
        val viewModelFact = HomeFragViewModelFact(repository)
        homeFragViewModel =
            ViewModelProvider(requireActivity(), viewModelFact).get(HomeFragViewModel::class.java)

        binding.donebt.setOnClickListener {

            val progressDialog = ProgressDialog(requireContext())
            progressDialog.setMessage("Please wait...")
            progressDialog.show()

            ioScope.launch {
                val res = homeFragViewModel.updateProfile("2", "rk@gmail.com", "RK")

//                if(res.isSuccessful) {
//                    progressDialog.dismiss()
//                    Log.e(TAG, "response: Success" + res.body())
//
//                }
//                else {
//                    progressDialog.dismiss()
//                    Log.e(TAG, "response: " + res.message())
//                }

                res.enqueue(object : Callback<String> {
                    override fun onResponse(
                        call: Call<String>,
                        response: Response<String>
                    ) {
                        progressDialog.dismiss()

                        Log.e(TAG, "onResponse")
                        Log.e(TAG, "call: " + call.toString())
                        Log.e(TAG, "response: " + response!!.body())
                    }

                    override fun onFailure(call: Call<String>, t: Throwable) {

                        progressDialog.dismiss()

                        Log.e(TAG, "onResponse")
                        Log.e(TAG, "call: " + call.toString())
                        Log.e(TAG, "t: " + Log.getStackTraceString(t))
                    }

                })
            }
        }*/

        return binding.root
    }
}