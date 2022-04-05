package com.example.kotlinroomdbcrud.navigationComponent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.ActivityBottomNavigationBinding

class bottomNavigation : AppCompatActivity() {

    var tag = this.javaClass.simpleName

    lateinit var binding: ActivityBottomNavigationBinding

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityBottomNavigationBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragment)
        binding.bottomNavigationView.setupWithNavController(navController)

        appBarConfiguration = AppBarConfiguration(setOf(R.id.homeFragment,R.id.profileFragment,R.id.settingFragment))
        setupActionBarWithNavController(navController,appBarConfiguration)
    }
}