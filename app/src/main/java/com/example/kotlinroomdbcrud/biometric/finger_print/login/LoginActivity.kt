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
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import com.example.kotlinroomdbcrud.biometric.finger_print.util.BiometricAuthListener
import com.example.kotlinroomdbcrud.biometric.finger_print.util.BiometricUtil
import com.example.kotlinroomdbcrud.biometric.finger_print.util.FailedLoginFormState
import com.example.kotlinroomdbcrud.biometric.finger_print.util.SampleAppUser
import com.example.kotlinroomdbcrud.biometric.finger_print.util.SuccessfulLoginFormState
import com.example.kotlinroomdbcrud.databinding.ActivityLoginBinding
import kotlinx.android.synthetic.main.activity_login.success

/**
 * After entering "valid" username and password, login button becomes enabled
 */
class LoginActivity : AppCompatActivity(), BiometricAuthListener {
    private val TAG = "LoginActivity"

    private lateinit var binding: ActivityLoginBinding
    private val loginWithPasswordViewModel by viewModels<LoginViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        showBiometricLoginOption()

        observeData()
        setupForLoginWithPassword()
    }

    private fun observeData() {
        loginWithPasswordViewModel.loginWithPasswordFormState.observe(this, Observer { loginState ->
            when (loginState) {
                is SuccessfulLoginFormState -> {
                    binding.login.isEnabled = loginState.isDataValid
                }

                is FailedLoginFormState -> {
                    loginState.usernameError?.let {
                        binding.username.error = getString(it)
                    }
                    loginState.passwordError?.let {
                        binding.password.error = getString(it)
                    }
                }
            }
        })

        loginWithPasswordViewModel.loginResult.observe(this) { result ->
            if (result.success) {
                updateApp(
                    "You successfully signed up using password as: user: " +
                            "${SampleAppUser.username} with fake token: ${SampleAppUser.fakeToken}"
                )
            }
        }
        Log.d(TAG, "Username ${SampleAppUser.username}; fake token ${SampleAppUser.fakeToken}")
    }

    private fun setupForLoginWithPassword() {
        binding.username.doAfterTextChanged {
            loginWithPasswordViewModel.onLoginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }
        binding.password.doAfterTextChanged {
            loginWithPasswordViewModel.onLoginDataChanged(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }

/*
        binding.buttonBiometricsLogin.setOnClickListener {
            BiometricUtil.showBiometricPrompt(
                activity = this,
                listener = this,
                cryptoObject = null,
                allowDeviceCredential = true
            )
        }
*/

        binding.login.setOnClickListener {

            loginWithPasswordViewModel.login(
                binding.username.text.toString(),
                binding.password.text.toString()
            )
        }
        Log.d(TAG, "Username ${SampleAppUser.username}; fake token ${SampleAppUser.fakeToken}")
    }

    /*
        private fun showBiometricLoginOption() {
            binding.buttonBiometricsLogin.visibility =
                if (BiometricUtil.isBiometricReady(this))
                    View.VISIBLE
                else
                    View.GONE
        }
    */

    private fun updateApp(successMsg: String) {
        binding.success.text = successMsg
        Toast.makeText(this, successMsg, Toast.LENGTH_SHORT).show()
        Log.d(TAG, "updateApp: $successMsg")
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