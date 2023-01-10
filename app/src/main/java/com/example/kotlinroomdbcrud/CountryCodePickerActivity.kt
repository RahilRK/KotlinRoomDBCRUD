package com.example.kotlinroomdbcrud

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.ak.ui.CountryCodePicker

class CountryCodePickerActivity : AppCompatActivity() {

    lateinit var countryCodePicker: CountryCodePicker

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_country_code_picker)

        countryCodePicker = findViewById(R.id.ccp_phone)
        Log.d("CountryCodePickerActivity", "onCreate: ${countryCodePicker.selectedCountryCode()}")
    }
}