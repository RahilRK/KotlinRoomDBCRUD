package com.example.kotlinroomdbcrud.dagger2.component

import com.example.kotlinroomdbcrud.dagger2.Dagger2Activity
import com.example.kotlinroomdbcrud.dagger2.module.NotifyServiceModule
import com.example.kotlinroomdbcrud.dagger2.module.UserRepositoryModule
import com.example.kotlinroomdbcrud.dagger2.myannotations.ActivityScope
import dagger.BindsInstance
import dagger.Subcomponent

@ActivityScope
@Subcomponent(modules = [UserRepositoryModule::class, NotifyServiceModule::class])
interface UserRegistrationServiceComponent {

//    fun getUserRegistrationService(): UserRegisterService

    fun dagger2ActivityInject(dagger2Activity: Dagger2Activity)

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance retryCount: Int): UserRegistrationServiceComponent
    }
}