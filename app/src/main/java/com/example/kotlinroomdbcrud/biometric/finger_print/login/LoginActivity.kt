/*
 * Copyright 2020 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.kotlinroomdbcrud.biometric.finger_print.login

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import com.example.kotlinroomdbcrud.biometric.finger_print.util.BiometricAuthListener
import com.example.kotlinroomdbcrud.biometric.finger_print.util.BiometricUtil
import com.example.kotlinroomdbcrud.databinding.ActivityLoginBinding

/**
 * After entering "valid" username and password, login button becomes enabled
 */
class LoginActivity : AppCompatActivity(), BiometricAuthListener {
    private val TAG = "LoginActivity"

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        setupForLoginWithPassword()

        showBiometricLoginOption()

        binding.buttonBiometricsLogin.setOnClickListener {
            BiometricUtil.showBiometricPrompt(
                activity = this,
                listener = this,
                cryptoObject = null,
                allowDeviceCredential = true
            )
        }
    }

    private fun showBiometricLoginOption() {
        binding.buttonBiometricsLogin.visibility =
            if (BiometricUtil.isBiometricReady(this))
                View.VISIBLE
            else
                View.GONE
    }

    override fun onBiometricAuthenticationSuccess(result: BiometricPrompt.AuthenticationResult) {
        Toast.makeText(this, "Biometric success", Toast.LENGTH_SHORT)
            .show()
    }

    override fun onBiometricAuthenticationError(errorCode: Int, errorMessage: String) {
        Toast.makeText(this, "Biometric login. Error: $errorMessage", Toast.LENGTH_SHORT)
            .show()
    }
}