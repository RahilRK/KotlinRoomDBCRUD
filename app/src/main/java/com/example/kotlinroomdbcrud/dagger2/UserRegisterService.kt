package com.example.kotlinroomdbcrud.dagger2

import javax.inject.Inject
import javax.inject.Named


class UserRegisterService @Inject constructor(
    @Named("room Obj")private val userRepository: UserRepositoryInterface,
    @Named("notification Obj")private val notifyService: NotifyServiceInterface
) {
    fun registerUser(email: String, password: String) {
        userRepository.saveUser(email,password)
        notifyService.sendMail("to@gmail.com","rahil@gmail.com","User registered")
    }
}