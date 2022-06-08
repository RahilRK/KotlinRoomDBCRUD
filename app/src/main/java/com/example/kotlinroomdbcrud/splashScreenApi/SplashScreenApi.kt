package com.example.kotlinroomdbcrud.splashScreenApi

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import com.airbnb.lottie.LottieAnimationView
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.flow.FlowViewModel
import com.example.kotlinroomdbcrud.flow.FlowViewModelFactory
import kotlinx.android.synthetic.main.activity_lottie_splash.*
import java.lang.Math.max

class SplashScreenApi : AppCompatActivity() {

    lateinit var viewModel: SplashScreenApiViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProvider(this).get(SplashScreenApiViewModel::class.java)

        val splashScreen = installSplashScreen().apply {
            setKeepVisibleCondition {
                viewModel.isLoading.value
            }
        }

        setContentView(R.layout.activity_splash_screen_api)

        // We set the OnExitAnimationListener to customize our splash screen animation.
        // This will allow us to take over the splash screen removal animation.
        splashScreen.setOnExitAnimationListener { vp ->
            Log.d("Splash", "onCreate: setOnExitAnimationListener")
            val lottieView = findViewById<LottieAnimationView>(R.id.lottieAnimationView)
            lottieView.visibility = View.VISIBLE
            lottieView.enableMergePathsForKitKatAndAbove(true)

            // Once the delay expires, we start the lottie animation
            lottieView.postDelayed({
                vp.view.alpha = 0f
                vp.iconView.alpha = 0f
                lottieView!!.playAnimation()
            }, 400L)

            // Finally we dismiss display our app content using a
            // nice circular reveal
            lottieView.addAnimatorListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator?) {
                    Log.d("Splash", "onCreate: onAnimationEnd")
                    /*val contentView = findViewById<View>(android.R.id.content)
                    val imageView = findViewById<ImageView>(R.id.imageView)

                    val animator = ViewAnimationUtils.createCircularReveal(
                        imageView,
                        contentView.width / 2,
                        contentView.height / 2,
                        0f,
                        max(contentView.width, contentView.height).toFloat()
                    ).setDuration(600)

                    imageView.visibility = View.VISIBLE
                    animator.start()*/
                }
            })
        }
    }
}