package com.example.kotlinroomdbcrud.intro_view_pager

import IntroAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.example.kotlinroomdbcrud.R
import com.google.android.material.button.MaterialButton
import com.zhpan.indicator.DrawableIndicator

class IntroActivity : AppCompatActivity() {

    lateinit var vpProductTour: ViewPager2
    lateinit var vectorIndicator: DrawableIndicator
    lateinit var btnNext: MaterialButton

    private var arrayList = arrayListOf<IntroModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)

        vpProductTour = findViewById(R.id.vpProductTour)
        vectorIndicator = findViewById(R.id.vectorIndicator)
        btnNext = findViewById(R.id.btnNext)

        addFakeData()
        setAdapter()
    }

    private fun addFakeData() {

        arrayList.add(
            IntroModel(
                image = R.drawable.screen_one_img,
                title1 = "Start earning with ",
                title2 = "Shifts2Go",
                txtText = "Use your hospitality experience to work\npay-as-you-go shifts at Hotels near you",
                underlineColor = ContextCompat.getColor(this,R.color.purple_500)
            )
        )
        arrayList.add(
            IntroModel(
                image = R.drawable.screen_two_img,
                title1 = "Connect with ",
                title2 = "Nearby Hotels",
                txtText = "Shifts2Go allows you to easily book shifts with\nany hotels in your area",
                underlineColor = ContextCompat.getColor(this,R.color.teal_200)
            )
        )
        arrayList.add(
            IntroModel(
                image = R.drawable.screen_three_img,
                title1 = "Fully Flexible ",
                title2 = "Schedule",
                txtText = "There is no set schedule, book shifts at your\nconvenience",
                underlineColor = ContextCompat.getColor(this,R.color.error)
            )
        )
    }

    private fun setAdapter() {
        vpProductTour.adapter = IntroAdapter(this,arrayList,vpProductTour)
        vectorIndicator.apply {
            val dp12 = resources.getDimensionPixelOffset(R.dimen.dp_8)
            setIndicatorGap(resources.getDimensionPixelOffset(R.dimen.dp_8))
            setIndicatorDrawable(R.drawable.banner_indicator_nornal, R.drawable.banner_indicator_focus)
            setIndicatorSize(dp12, dp12, resources.getDimensionPixelOffset(R.dimen.dp_16), dp12)
            setupWithViewPager(vpProductTour)
        }
    }
}