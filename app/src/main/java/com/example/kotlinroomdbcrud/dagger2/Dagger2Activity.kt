package com.example.kotlinroomdbcrud.dagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.kotlinroomdbcrud.util.Application
import com.example.kotlinroomdbcrud.R
import javax.inject.Inject

class Dagger2Activity : AppCompatActivity() {

    @Inject
    lateinit var userRegisterService: UserRegisterService

    @Inject
    lateinit var emailService1: EmailService

    @Inject
    lateinit var emailService2: EmailService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dagger2)

        //todo old way to make Obj
        /*val userRepository = UserRepository()
        val emailService = EmailService()

        val userRegisterService = UserRegisterService(userRepository,emailService)*/

        //todo dagger way to make Obj
        /*val component = DaggerUserRegistrationServiceComponent.builder().build().getUserRegistrationService()
        component.registerUser("rahilmithani007@gmail.com", "123456")*/

        val appComponent = (application as Application).appComponent

        val userRegistrationServiceComponent = appComponent
            .getUserRegistrationServiceComponentFactory()
            .create(4)

        userRegistrationServiceComponent.dagger2ActivityInject(this)
        userRegisterService.registerUser("rahilmithani007@gmail.com", "123456")
    }
}