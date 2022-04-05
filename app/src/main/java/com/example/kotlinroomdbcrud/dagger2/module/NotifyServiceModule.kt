package com.example.kotlinroomdbcrud.dagger2.module

import com.example.kotlinroomdbcrud.dagger2.EmailService
import com.example.kotlinroomdbcrud.dagger2.NotificationService
import com.example.kotlinroomdbcrud.dagger2.NotifyServiceInterface
import com.example.kotlinroomdbcrud.dagger2.myannotations.ActivityScope
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Scope

@Module
class NotifyServiceModule() {

    @ActivityScope
    @Named("notification Obj")
    @Provides
    fun getNotificationService(retryCount: Int): NotifyServiceInterface {
        return NotificationService(retryCount)
    }

    @Named("email Obj")
    @Provides
    fun getEmailService(emailService: EmailService): NotifyServiceInterface {
        return emailService
    }
}