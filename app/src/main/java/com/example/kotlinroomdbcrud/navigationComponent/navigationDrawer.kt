package com.example.kotlinroomdbcrud.navigationComponent

import android.graphics.drawable.AdaptiveIconDrawable
import android.graphics.drawable.ColorDrawable
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.databinding.ActivityNavigationDrawerBinding
import java.security.AccessController

class navigationDrawer : AppCompatActivity() {

    var tag = this.javaClass.simpleName

    lateinit var binding: ActivityNavigationDrawerBinding

    lateinit var navController: NavController
    lateinit var appBarConfiguration: AppBarConfiguration
    lateinit var listener: NavController.OnDestinationChangedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationDrawerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.fragment)
        binding.navigationView.setupWithNavController(navController)

        //todo for hamburger menu, attach fragments
        appBarConfiguration = AppBarConfiguration(navController.graph,binding.drawerLayout)
        setupActionBarWithNavController(navController,appBarConfiguration)

        //todo nav menu onClick event
        listener = NavController.OnDestinationChangedListener { controller, destination, arguments ->

            if(destination.id == R.id.firstFragment) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.purple_500)))
                }
            }
            else if(destination.id == R.id.secondFragment) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    supportActionBar?.setBackgroundDrawable(ColorDrawable(getColor(R.color.teal_200)))
                }
            }
        }
    }

    //todo for expand/collapse menu
    override fun onSupportNavigateUp(): Boolean {

        val navController = findNavController(R.id.fragment)
        return navController.navigateUp(appBarConfiguration)||super.onSupportNavigateUp()
    }

    override fun onResume() {
        super.onResume()

        navController.addOnDestinationChangedListener(listener)
    }

    override fun onPause() {
        super.onPause()

        navController.removeOnDestinationChangedListener(listener)
    }
}