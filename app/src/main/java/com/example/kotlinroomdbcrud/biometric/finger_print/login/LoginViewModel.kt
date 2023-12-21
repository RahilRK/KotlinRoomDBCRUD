package com.example.kotlinroomdbcrud.biometric.finger_print.login

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.kotlinroomdbcrud.R
import com.example.kotlinroomdbcrud.biometric.finger_print.util.FailedLoginFormState
import com.example.kotlinroomdbcrud.biometric.finger_print.util.LoginFormState
import com.example.kotlinroomdbcrud.biometric.finger_print.util.LoginResult
import com.example.kotlinroomdbcrud.biometric.finger_print.util.SampleAppUser
import com.example.kotlinroomdbcrud.biometric.finger_print.util.SuccessfulLoginFormState

class LoginViewModel: ViewModel() {

    private val _loginForm = MutableLiveData<LoginFormState>()
    val loginWithPasswordFormState: LiveData<LoginFormState> = _loginForm
    fun onLoginDataChanged(username: String, password: String) {
        if (!isUserNameValid(username)) {
            _loginForm.value = FailedLoginFormState(usernameError = R.string.invalid_username)
        } else if (!isPasswordValid(password)) {
            _loginForm.value = FailedLoginFormState(passwordError = R.string.invalid_password)
        } else {
            _loginForm.value = SuccessfulLoginFormState(isDataValid = true)
        }
    }

    // A placeholder username validation check
    private fun isUserNameValid(username: String): Boolean {
        return if (username.contains('@')) {
            Patterns.EMAIL_ADDRESS.matcher(username).matches()
        } else {
            username.isNotBlank()
        }
    }

    // A placeholder password validation check
    private fun isPasswordValid(password: String): Boolean {
        return password.length > 5
    }

    private val _loginResult = MutableLiveData<LoginResult>()
    val loginResult: LiveData<LoginResult> = _loginResult
    fun login(username: String, password: String) {
        if (isUserNameValid(username) && isPasswordValid(password)) {
            // Normally this method would asynchronously send this to your server and your sever
            // would return a token. For high sensitivity apps such as banking, you would keep that
            // token in transient memory similar to my SampleAppUser object. This way the user
            // must login each time they start the app.
            // In this sample, we don't call a server. Instead we use a fake token that we set
            // right here:

            SampleAppUser.username = username
            SampleAppUser.fakeToken = java.util.UUID.randomUUID().toString()
            _loginResult.value = LoginResult(true)
        } else {
            _loginResult.value = LoginResult(false)
        }
    }

}