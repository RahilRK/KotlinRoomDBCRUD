package com.example.kotlinroomdbcrud.dagger2.component

import com.example.kotlinroomdbcrud.dagger2.module.AnalyticsModule
import com.example.kotlinroomdbcrud.dagger2.myclasses.AnalyticsService
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AnalyticsModule::class])
interface AppComponent {

    fun getUserRegistrationServiceComponentFactory  (): UserRegistrationServiceComponent.Factory
}