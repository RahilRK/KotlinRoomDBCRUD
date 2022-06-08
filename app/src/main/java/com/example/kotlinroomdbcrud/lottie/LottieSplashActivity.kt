package com.example.kotlinroomdbcrud.lottie

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.ImageView
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.airbnb.lottie.LottieAnimationView
import com.example.kotlinroomdbcrud.R
import kotlinx.android.synthetic.main.activity_lottie_splash.*
import java.lang.Integer.max

class LottieSplashActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lottie_splash)


        Handler(Looper.getMainLooper()).postDelayed({
            lottieAnimationView.visibility = View.VISIBLE
            lottieAnimationView.playAnimation()
        },0)
    }
}