package com.example.kotlinroomdbcrud.dagger2.module

import com.example.kotlinroomdbcrud.dagger2.myclasses.AnalyticsService
import com.example.kotlinroomdbcrud.dagger2.myclasses.MixPanel
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AnalyticsModule {

    @Singleton
    @Provides
    fun getAnalyticService(): AnalyticsService {
        return MixPanel()
    }
}